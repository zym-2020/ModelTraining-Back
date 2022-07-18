package njnu.edu.modeltraining.service.impl;

import lombok.SneakyThrows;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.LocalUpload;
import njnu.edu.modeltraining.service.RedisService;
import njnu.edu.modeltraining.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/10:15
 * @Description:
 */
@Repository
public class UploadServiceImpl implements UploadService {

    @Value("${uploadAddress}")
    String uploadAddress;

    @Value("${tempAddress}")
    String tempAddress;

    @Value("${homeworkAddress}")
    String homeworkAddress;

    @Autowired
    RedisService redisService;

    @Override
    public String uploadImg(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = file.getInputStream();
            File f = new File(uploadAddress + "img");
            if(!f.exists()) {
                f.mkdirs();
            }
            out = new FileOutputStream(uploadAddress + "img\\" + uuid + "." + suffix);
            int len = -1;
            byte[] bytes = new byte[1024];
            while((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
        return uuid + "." + suffix;
    }

    @Override
    public void getImg(String fileName, HttpServletResponse response) {
        String fileAddress = uploadAddress + "img\\" + fileName;
        File file = new File(fileAddress);
        if(!file.exists()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        InputStream in = null;
        ServletOutputStream sos = null;
        try {
            in = new FileInputStream(file);
            sos = response.getOutputStream();
            byte[] b = new byte[1024];
            while(in.read(b) != -1) {
                sos.write(b);
            }
            sos.flush();
            in.close();
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(sos != null) {
                    sos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
    }

    @Override
    public void uploadFile(MultipartFile file, String number, String name, String teamId) {
        String dir = tempAddress + teamId + "_" + number;
        LocalUpload.UploadFile(file, name, dir);
    }

    @Override
    public String mergeFiles(String teamId, String number, int total, String suffix) {
        String uuid = UUID.randomUUID().toString();
        String from = tempAddress + teamId + "_" + number;
        String to = homeworkAddress + teamId + "_" + number + suffix;
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                redisService.set(uuid, 0, 60*3l);
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uuid, 1, 60*3l);
                } else {
                    redisService.set(uuid, -1, 60*3l);
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
}
