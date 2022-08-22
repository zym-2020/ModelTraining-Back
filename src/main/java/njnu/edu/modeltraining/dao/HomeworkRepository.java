package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.Homework;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/24/16:27
 * @Description:
 */
@Repository
public interface HomeworkRepository extends MongoRepository<Homework, String> {
    Homework findByMemberIdAndNumber(String memberId, String number);

    List<Homework> findByMemberId(String memberId);
}
