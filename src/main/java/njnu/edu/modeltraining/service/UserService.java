package njnu.edu.modeltraining.service;

import njnu.edu.modeltraining.pojo.User;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/18:58
 * @Description:
 */
public interface UserService {
    String login(User user);

    Map<String, Object> getUserInfo(String email);
}
