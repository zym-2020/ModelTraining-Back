package njnu.edu.modeltraining.controller;

import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/19:10
 * @Description:
 */
@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @AuthCheck
    @RequestMapping(value = "/uploadHomework", method = RequestMethod.POST)
    public JsonResult uploadHomework(@RequestParam MultipartFile file, @RequestParam String type, @JwtTokenParser("email") String email) {
        homeworkService.uploadHomework(file, type, email);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/downloadHomework/{fileName}", method = RequestMethod.GET)
    public void downloadHomework(@PathVariable String fileName, HttpServletResponse response) {
        homeworkService.downloadHomework(fileName, response);
    }

}
