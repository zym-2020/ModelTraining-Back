package njnu.edu.modeltraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.service.CertService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/21:03
 * @Description:
 */
@Service
@Slf4j
public class CertServiceImpl implements CertService {
    @Value("${certificateAddress}")
    String certificateAddress;

    @Override
    public void download(String certName, HttpServletResponse response) {
        String address = certificateAddress + certName;
        File file = new File(address);
        if (!file.exists()) throw new MyException(ResultEnum.NO_OBJECT);
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(certName, "UTF-8"));
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
