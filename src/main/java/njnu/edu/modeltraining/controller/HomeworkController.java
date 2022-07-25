package njnu.edu.modeltraining.controller;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/24/16:25
 * @Description:
 */
@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @AuthCheck
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam MultipartFile file, @RequestParam String number, @RequestParam String name, @JwtTokenParser("memberId") String memberId) {
        homeworkService.uploadFile(file, number, name, memberId);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/mergeFiles", method = RequestMethod.POST)
    public JsonResult mergeFile(@RequestBody JSONObject jsonObject, @JwtTokenParser("memberId") String memberId) {
        String number = jsonObject.getString("number");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(homeworkService.mergeFiles(memberId, number, total, name));
    }

    @AuthCheck
    @RequestMapping(value = "/checkState/{uuid}", method = RequestMethod.GET)
    public JsonResult checkState(@PathVariable String uuid) {
        return ResultUtils.success(homeworkService.checkState(uuid));
    }

    @AuthCheck
    @RequestMapping(value = "/clearTemp/{number}", method = RequestMethod.POST)
    public JsonResult clearTemp(@PathVariable String number, @JwtTokenParser("memberId") String memberId) {
        homeworkService.clearTemp(memberId, number);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/getHomework", method = RequestMethod.GET)
    public JsonResult getHomework(@JwtTokenParser("memberId") String memberId) {
        return ResultUtils.success(homeworkService.getHomework(memberId));
    }

    @RequestMapping(value = "/download/{number}", method = RequestMethod.GET)
    public JsonResult download(@PathVariable String number, @JwtTokenParser("memberId") String memberId, HttpServletResponse response) {
        homeworkService.download(memberId, number, response);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/removeFile/{number}", method = RequestMethod.DELETE)
    public JsonResult removeFile(@PathVariable String number, @JwtTokenParser("memberId") String memberId) {
        homeworkService.removeFile(memberId, number);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/commit/{number}", method = RequestMethod.PATCH)
    public JsonResult commit(@PathVariable String number, @JwtTokenParser("memberId") String memberId) {
        homeworkService.commit(memberId, number);
        return ResultUtils.success();
    }
}
