package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:46
 * @Description:
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByTeamId(String teamId);
}
