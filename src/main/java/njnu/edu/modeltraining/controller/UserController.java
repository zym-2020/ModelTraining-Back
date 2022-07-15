package njnu.edu.modeltraining.controller;

import com.alibaba.fastjson.JSONObject;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/16:37
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody JSONObject jsonObject) {
        return ResultUtils.success(userService.login(jsonObject.getString("teamId"), jsonObject.getString("name")));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody User user) {
        userService.addUser(user);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JsonResult getUserInfo(@JwtTokenParser("teamId") String teamId, @JwtTokenParser("name") String name) {
        Map<String, String> map = new HashMap<>();
        map.put("teamId", teamId);
        map.put("name", name);
        return ResultUtils.success(map);
    }

    @AuthCheck
    @RequestMapping(value = "/getTeamInfo", method = RequestMethod.GET)
    public JsonResult getTeamInfo(@JwtTokenParser("teamId") String teamId) {
        return ResultUtils.success(userService.getTeamInfo(teamId));
    }

}
