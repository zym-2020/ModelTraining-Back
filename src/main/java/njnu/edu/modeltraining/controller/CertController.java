package njnu.edu.modeltraining.controller;

import njnu.edu.modeltraining.service.CertService;
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
 * @Date: 2023/07/31/21:02
 * @Description:
 */
@RequestMapping("/cert")
@RestController
public class CertController {
    @Autowired
    CertService certService;

    @RequestMapping(value = "/download/{certName}", method = RequestMethod.GET)
    public void download(@PathVariable String certName, HttpServletResponse response) {
        certService.download(certName, response);
    }
}
