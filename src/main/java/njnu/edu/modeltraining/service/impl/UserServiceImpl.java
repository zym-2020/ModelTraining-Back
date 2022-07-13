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
import njnu.edu.modeltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void addUser(User user) {
        user.setName(Encrypt.md5(user.getName()));
        userRepository.save(user);
    }

    @Override
    public JSONObject login(String teamId, String name) {
        List<User> users = userRepository.findAllByTeamId(teamId);
        if(users == null) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        name = Encrypt.md5(name);
        for(User user : users) {
            if(name.equals(user.getName())) {
                ApplyHomework applyHomework = applyHomeworkRepository.findByTeamId(teamId);
                if(applyHomework == null) {
                    applyHomework = new ApplyHomework();
                    applyHomework.setState(0);
                    applyHomework.setTeamId(teamId);
                    applyHomeworkRepository.save(applyHomework);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", JwtTokenUtil.generateTokenByUser(user));
                jsonObject.put("info", user);
                return jsonObject;
            }
        }
        throw new MyException(ResultEnum.NO_OBJECT);
    }
}
