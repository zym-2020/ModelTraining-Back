package njnu.edu.modeltraining.service.impl;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.Encrypt;
import njnu.edu.modeltraining.common.utils.JwtTokenUtil;
import njnu.edu.modeltraining.dao.ApplyHomeworkRepository;
import njnu.edu.modeltraining.dao.UserRepository;
import njnu.edu.modeltraining.pojo.ApplyHomework;
import njnu.edu.modeltraining.pojo.User;
import njnu.edu.modeltraining.service.RedisService;
import njnu.edu.modeltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:55
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplyHomeworkRepository applyHomeworkRepository;

    @Autowired
    RedisService redisService;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public JSONObject login(String email, String name) {
        User user = (User) redisService.get(email);
        if(user == null) {
            user = userRepository.findByEmail(email);
            if(user == null) {
                throw new MyException(ResultEnum.NO_OBJECT);
            }
        }
        redisService.set(email, user, 60*24*7l);

        if(user.getName().equals(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", JwtTokenUtil.generateTokenByUser(user));
            jsonObject.put("info", user);
            return jsonObject;
        } else {
            throw new MyException(ResultEnum.USER_PASSWORD_NOT_MATCH);
        }
    }

    @Override
    public List<Map<String, String>> getTeamInfo(String teamId) {
        List<User> users = userRepository.findAllByTeamId(teamId);
        List<Map<String, String>> result = new ArrayList<>();
        for (User user : users) {
            Map<String, String> map = new HashMap<>();
            map.put("name", user.getName());
            map.put("teamId", user.getTeamId());
            map.put("memberId", user.getMemberId());
            result.add(map);
        }
        return result;
    }
}
