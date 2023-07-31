package njnu.edu.modeltraining.dao;

import njnu.edu.modeltraining.pojo.Homework;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/19:13
 * @Description:
 */
@Repository
public interface HomeworkMapper {
    void insertHomework(@Param("homework") Homework homework);

    Homework queryHomework(@Param("email") String email, @Param("type") String type);

    void updateHomework(@Param("email") String email, @Param("type") String type, @Param("address") String address);
}
