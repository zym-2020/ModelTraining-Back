package njnu.edu.modeltraining.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/10:13
 * @Description:
 */
public interface UploadService {
    String uploadImg(MultipartFile file);

    void getImg(String fileName, HttpServletResponse response);
}
