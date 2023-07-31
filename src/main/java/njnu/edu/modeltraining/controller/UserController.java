package njnu.edu.modeltraining.controller;

import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.pojo.User;
import njnu.edu.modeltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/18:59
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody User user) {
        return ResultUtils.success(userService.login(user));
    }

    @AuthCheck
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JsonResult getUserInfo(@JwtTokenParser("email") String email) {
        return ResultUtils.success(userService.getUserInfo(email));
    }
}
