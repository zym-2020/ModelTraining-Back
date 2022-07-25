package njnu.edu.modeltraining.service;

import njnu.edu.modeltraining.pojo.Homework;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/24/16:31
 * @Description:
 */
public interface HomeworkService {

    void uploadFile(MultipartFile file, String number, String name, String memberId);

    String mergeFiles(String memberId, String number, int total, String name);

    int checkState(String uuid);

    void clearTemp(String memberId, String number);

    List<Homework> getHomework(String memberId);

    void download(String memberId, String number, HttpServletResponse response);

    void removeFile(String memberId, String number);

    void commit(String memberId, String number);
}
