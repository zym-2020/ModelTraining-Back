package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/19:00
 * @Description:
 */
@Repository
public interface UserMapper {
    User queryUserByEmail(@Param("email") String email);
}
