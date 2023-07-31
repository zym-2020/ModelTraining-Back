package njnu.edu.modeltraining.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/21:03
 * @Description:
 */
public interface CertService {
    void download(String certName, HttpServletResponse response);
}
