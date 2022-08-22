package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.Certificate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/08/01/15:55
 * @Description:
 */
public interface CertificateRepository extends MongoRepository<Certificate, String> {
    Certificate findByUserIdAndType(String userId, String type);
}
