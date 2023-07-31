package njnu.edu.modeltraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.dao.HomeworkMapper;
import njnu.edu.modeltraining.pojo.Homework;
import njnu.edu.modeltraining.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/19:13
 * @Description:
 */
@Service
@Slf4j
public class HomeworkServiceImpl implements HomeworkService {
    @Value("${uploadAddress}")
    String uploadAddress;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Override
    public void uploadHomework(MultipartFile file, String type, String email) {
        String uuid = UUID.randomUUID().toString();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String address = uploadAddress + uuid + suffix;
        try {
            FileOutputStream fos = new FileOutputStream(address);
            InputStream inputStream = file.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();
            fos.close();
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        }
        Homework homework = homeworkMapper.queryHomework(email, type);
        if (homework == null) {
            homeworkMapper.insertHomework(new Homework(email, type, uuid + suffix));
        } else {
            homeworkMapper.updateHomework(email, type, uuid + suffix);
        }
    }

    @Override
    public void downloadHomework(String fileName, HttpServletResponse response) {
        String address = uploadAddress + fileName;
        File file = new File(address);
        if (!file.exists()) throw new MyException(ResultEnum.NO_OBJECT);
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        }
    }
}
