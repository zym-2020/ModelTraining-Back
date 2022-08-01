package njnu.edu.modeltraining.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/08/01/15:54
 * @Description:
 */
public interface CertificateService {
    Map<String, String> getAddress(String userId);

    void getCertificate(String number, HttpServletResponse response);
}
