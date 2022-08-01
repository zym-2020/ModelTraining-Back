package njnu.edu.modeltraining.service.impl;

import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.dao.CertificateRepository;
import njnu.edu.modeltraining.pojo.Certificate;
import njnu.edu.modeltraining.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/08/01/15:54
 * @Description:
 */
@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;

    @Value("${certificateAddress}")
    String certificateAddress;

    @Override
    public Map<String, String> getAddress(String userId) {
        Map<String, String> map = new HashMap<>();
        Certificate train = certificateRepository.findByUserIdAndType(userId, "train");
        Certificate apply = certificateRepository.findByUserIdAndType(userId, "apply");
        Certificate develop = certificateRepository.findByUserIdAndType(userId, "develop");
        if(train != null) {
            map.put("train", train.getNumber());
        } else {
            map.put("train", "");
        }
        if(apply != null) {
            map.put("apply", apply.getNumber());
        } else {
            map.put("apply", "");
        }
        if(develop != null) {
            map.put("develop", develop.getNumber());
        } else {
            map.put("develop", "");
        }
        return map;
    }

    @Override
    public void getCertificate(String number, HttpServletResponse response) {
        String path = certificateAddress + number + ".pptx";
        File file = new File(path);
        if(!file.exists()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        InputStream in = null;
        ServletOutputStream sos = null;
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(number + ".pptx", "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            in = new FileInputStream(file);
            sos = response.getOutputStream();
            byte[] bytes = new byte[1024];
            while((in.read(bytes)) > -1) {
                sos.write(bytes);
            }
            sos.flush();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
    }
}
