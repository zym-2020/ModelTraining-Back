package njnu.edu.modeltraining.service;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.pojo.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:53
 * @Description:
 */

public interface UserService {

    void addUser(User user);

    User login(String account, String password);
}