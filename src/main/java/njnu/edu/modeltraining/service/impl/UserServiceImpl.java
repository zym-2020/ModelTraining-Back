package njnu.edu.modeltraining.service.impl;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.Encrypt;
import njnu.edu.modeltraining.common.utils.JwtTokenUtil;
import njnu.edu.modeltraining.dao.UserRepository;
import njnu.edu.modeltraining.pojo.User;
import njnu.edu.modeltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void addUser(User user) {
        String account = user.getAccount();
        User temp = userRepository.findByAccount(account);
        if(temp != null) {
            throw new MyException(ResultEnum.EXIST_OBJECT);
        }
        user.setPassword(Encrypt.md5(user.getPassword()));
        user.setApplyId("");
        user.setDevelopId("");
        user.setHomeworkId("");
        userRepository.save(user);
    }

    @Override
    public JSONObject login(String account, String password) {
        User user = userRepository.findByAccount(account);
        if(user == null) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        password = Encrypt.md5(password);
        if(password.equals(user.getPassword())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", JwtTokenUtil.generateTokenByUser(user));
            jsonObject.put("info", user);
            return jsonObject;
        } else {
            throw new MyException(ResultEnum.USER_PASSWORD_NOT_MATCH);
        }
    }
}
