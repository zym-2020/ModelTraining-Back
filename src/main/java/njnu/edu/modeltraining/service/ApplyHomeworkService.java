package njnu.edu.modeltraining.service;

import njnu.edu.modeltraining.pojo.ApplyHomework;
import njnu.edu.modeltraining.pojo.support.Description;
import njnu.edu.modeltraining.pojo.support.Method;
import njnu.edu.modeltraining.pojo.support.Result;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:46
 * @Description:
 */
public interface ApplyHomeworkService {
    ApplyHomework getByTeamId(String teamId);

    void saveDescription(String id, Description description);

    void saveMethod(String id, Method method);

    void saveResult(String id, Result result);
}
