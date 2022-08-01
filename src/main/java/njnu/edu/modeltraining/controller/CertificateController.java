package njnu.edu.modeltraining.controller;

import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/08/01/15:53
 * @Description:
 */
@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "/getAddress", method = RequestMethod.GET)
    public JsonResult getAddress(@JwtTokenParser("id") String userId) {
        return ResultUtils.success(certificateService.getAddress(userId));
    }

    @RequestMapping(value = "/getCertificate/{number}", method = RequestMethod.GET)
    public void getCertificate(@PathVariable String number, HttpServletResponse response) {
        certificateService.getCertificate(number, response);
    }
}
