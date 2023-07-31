package njnu.edu.modeltraining.service.impl;

import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.Encrypt;
import njnu.edu.modeltraining.common.utils.JwtTokenUtil;
import njnu.edu.modeltraining.dao.UserMapper;
import njnu.edu.modeltraining.pojo.User;
import njnu.edu.modeltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/18:59
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String login(User user) {
        if (user.getEmail() == null) throw new MyException(ResultEnum.NO_OBJECT);
        User resultUser = userMapper.queryUserByEmail(user.getEmail());
        if (resultUser.getPassword().equals(Encrypt.md5(user.getPassword()))) {
            return JwtTokenUtil.generateTokenByUser(resultUser);
        } else throw new MyException(ResultEnum.USER_PASSWORD_NOT_MATCH);
    }

    @Override
    public Map<String, Object> getUserInfo(String email) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.queryUserByEmail(email);
        if (user == null) throw new MyException(ResultEnum.TOKEN_WRONG);
        map.put("email", user.getEmail());
        map.put("name", user.getName());
        return map;
    }
}
