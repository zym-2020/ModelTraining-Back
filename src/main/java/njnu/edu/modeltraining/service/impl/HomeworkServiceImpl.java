package njnu.edu.modeltraining.service.impl;

import lombok.SneakyThrows;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.LocalUpload;
import njnu.edu.modeltraining.dao.HomeworkRepository;
import njnu.edu.modeltraining.pojo.Homework;
import njnu.edu.modeltraining.service.HomeworkService;
import njnu.edu.modeltraining.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/24/16:31
 * @Description:
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Value("${tempAddress}")
    String tempAddress;

    @Value("${homeworkAddress}")
    String homeworkAddress;

    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    RedisService redisService;

    @Override
    public void uploadFile(MultipartFile file, String number, String name, String memberId) {
        String dir = tempAddress + memberId + "_" + number;
        LocalUpload.UploadFile(file, name, dir);
    }

    @Override
    public String mergeFiles(String memberId, String number, int total, String name) {
        String uuid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + memberId + "_" + number;
        String to = homeworkAddress + memberId + "_" + number + "." + suffix;
        LocalUpload.deleteFolder(to);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                redisService.set(uuid, 0, 60l);
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uuid, 1, 60l);
                    Homework homework = homeworkRepository.findByMemberIdAndNumber(memberId, number);
                    if(homework == null) {
                        homework = new Homework(null, memberId + "_" + number + "." + suffix, name, memberId, number, 0);
                    } else {
                        homework.setFileName(memberId + "_" + number + "." + suffix);
                        homework.setName(name);
                    }
                    homeworkRepository.save(homework);

                } else {
                    redisService.set(uuid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uuid;
    }

    @Override
    public int checkState(String uuid) {
        Integer state = (Integer) redisService.get(uuid);
        if(state == null) {
            return -1;
        } else {
            if(state == 1 || state == -1) {
                redisService.del(uuid);
            }
            return state;
        }
    }

    @Override
    public void clearTemp(String memberId, String number) {
        String dir = tempAddress + memberId + "_" + number;
        File file = new File(dir);
        if(file.exists()) {
            LocalUpload.deleteFolder(dir);
        }
    }

    @Override
    public List<Homework> getHomework(String memberId) {
        return homeworkRepository.findByMemberId(memberId);
    }

    @Override
    public void download(String memberId, String number, HttpServletResponse response) {
        Homework homework = homeworkRepository.findByMemberIdAndNumber(memberId, number);
        if(homework == null) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        String name = homework.getName();
        String fileName = homework.getFileName();
        File file = new File(homeworkAddress + fileName);
        if(!file.exists()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        InputStream in = null;
        ServletOutputStream sos = null;
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            in = new FileInputStream(file);
            sos = response.getOutputStream();
            byte[] bytes = new byte[1024];
            while((in.read(bytes)) > -1) {
                sos.write(bytes);
            }
            sos.flush();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
    }

    @Override
    public void removeFile(String memberId, String number) {
        Homework homework = homeworkRepository.findByMemberIdAndNumber(memberId, number);
        if (homework == null) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        File file = new File(homeworkAddress + homework.getFileName());
        if(file.exists()) {
            LocalUpload.deleteFolder(homeworkAddress + homework.getFileName());
        }
        homework.setFileName("");
        homeworkRepository.save(homework);
    }

    @Override
    public void commit(String memberId, String number) {
        Homework homework = homeworkRepository.findByMemberIdAndNumber(memberId, number);
        if (homework == null) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        if(homework.getFileName().equals("")) {
            throw new MyException(-99, "提交为空");
        }
        homework.setState(1);
        homeworkRepository.save(homework);
    }
}
