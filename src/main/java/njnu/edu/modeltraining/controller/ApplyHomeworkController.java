package njnu.edu.modeltraining.controller;

import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.pojo.support.Description;
import njnu.edu.modeltraining.service.ApplyHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:43
 * @Description:
 */
@RestController
@RequestMapping("/apply")
public class ApplyHomeworkController {
    @Autowired
    ApplyHomeworkService applyHomeworkService;

    @AuthCheck
    @RequestMapping(value = "/getByTeamId", method = RequestMethod.GET)
    public JsonResult getByTeamId(@JwtTokenParser("teamId") String teamId) {
        return ResultUtils.success(applyHomeworkService.getByTeamId(teamId));
    }

    @AuthCheck
    @RequestMapping(value = "/saveDescription/{id}", method = RequestMethod.PATCH)
    public JsonResult saveDescription(@RequestBody Description description, @PathVariable String id) {
        applyHomeworkService.saveDescription(id, description);
        return ResultUtils.success();
    }

}
