package njnu.edu.modeltraining.controller;

import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/10:10
 * @Description:
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @CrossOrigin
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JsonResult uploadImg(@RequestParam MultipartFile file) {
        return ResultUtils.success(uploadService.uploadImg(file));
    }

    @CrossOrigin
    @RequestMapping(value = "/getImg/{fileName}", method = RequestMethod.GET)
    public void getImg(@PathVariable String fileName, HttpServletResponse response) {
        uploadService.getImg(fileName, response);
    }

    @AuthCheck
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam MultipartFile file, @RequestParam String number, @RequestParam String name, @JwtTokenParser("teamId") String teamId) {
        uploadService.uploadFile(file, number, name, teamId);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/mergeFiles", method = RequestMethod.POST)
    public JsonResult mergeFile(@RequestBody JSONObject jsonObject, @JwtTokenParser("teamId") String teamId) {
        String number = jsonObject.getString("number");
        String suffix = jsonObject.getString("suffix");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(uploadService.mergeFiles(teamId, number, total, suffix));
    }

    @AuthCheck
    @RequestMapping(value = "/checkState/{uuid}", method = RequestMethod.GET)
    public JsonResult checkState(@PathVariable String uuid) {
        return ResultUtils.success(uploadService.checkState(uuid));
    }
}
