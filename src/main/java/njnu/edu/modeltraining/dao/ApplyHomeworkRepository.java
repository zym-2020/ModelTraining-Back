package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.ApplyHomework;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:45
 * @Description:
 */

@Repository
public interface ApplyHomeworkRepository extends MongoRepository<ApplyHomework, String> {
    ApplyHomework findByTeamId(String teamId);
}
