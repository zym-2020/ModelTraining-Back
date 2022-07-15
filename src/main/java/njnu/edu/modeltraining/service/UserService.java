package njnu.edu.modeltraining.service;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:53
 * @Description:
 */

public interface UserService {

    void addUser(User user);

    JSONObject login(String teamId, String name);

    List<Map<String, String>> getTeamInfo(String teamId);
}
