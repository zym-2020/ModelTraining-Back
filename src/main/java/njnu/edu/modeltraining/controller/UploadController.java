package njnu.edu.modeltraining.controller;

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
}
