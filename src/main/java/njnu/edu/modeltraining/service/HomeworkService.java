package njnu.edu.modeltraining.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/19:13
 * @Description:
 */
public interface HomeworkService {
    void uploadHomework(MultipartFile file, String type, String email);

    void downloadHomework(String fileName, HttpServletResponse response);
}
