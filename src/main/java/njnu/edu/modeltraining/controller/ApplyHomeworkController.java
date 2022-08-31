package njnu.edu.modeltraining.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import njnu.edu.modeltraining.common.auth.AuthCheck;
import njnu.edu.modeltraining.common.resolver.JwtTokenParser;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultUtils;
import njnu.edu.modeltraining.pojo.support.*;
import njnu.edu.modeltraining.pojo.support.method.ComputeResource;
import njnu.edu.modeltraining.pojo.support.method.DataResource;
import njnu.edu.modeltraining.pojo.support.method.ModelResource;
import njnu.edu.modeltraining.pojo.support.method.Process;
import njnu.edu.modeltraining.pojo.support.researcher.Person;
import njnu.edu.modeltraining.service.ApplyHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @RequestMapping(value = "/getById", method = RequestMethod.PATCH)
    public JsonResult getById( @JwtTokenParser("id") String id) {
        return ResultUtils.success(applyHomeworkService.getById(id));
    }
    @AuthCheck
    @RequestMapping(value = "/saveDescription/{id}", method = RequestMethod.PATCH)
    public JsonResult saveDescription(@RequestBody Description description, @PathVariable String id) {
        applyHomeworkService.saveDescription(id, description);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/saveTopic/{id}", method = RequestMethod.PATCH)
    public JsonResult saveTopic(@RequestBody Topic topic, @PathVariable String id) {
        applyHomeworkService.saveTopic(id, topic);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/saveProcess/{id}", method = RequestMethod.PATCH)
    public JsonResult saveProcess(@RequestBody JSONArray jsonArray, @PathVariable String id) {
        List<Process> processes = new ArrayList<>();
        for(int i = 0;i < jsonArray.size();i++) {
            processes.add(jsonArray.getObject(i, Process.class));
        }
//        Process[] p = (Process[]) jsonArray.toArray();
//        List<Process> processes = Arrays.asList(p);
        applyHomeworkService.saveProcess(id, processes);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/saveResult/{id}", method = RequestMethod.PATCH)
    public JsonResult saveResult(@RequestBody Result result, @PathVariable String id) {
        applyHomeworkService.saveResult(id, result);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/saveModelResources/{id}",method = RequestMethod.PATCH)
    public JsonResult saveModelResources(@RequestBody ModelResource modelResource,@PathVariable String id){
        applyHomeworkService.saveModelResources(id, modelResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/deleteModelResources/{id}",method = RequestMethod.PATCH)
    public JsonResult deleteModelResources(@RequestBody ModelResource modelResource, @PathVariable String id){
        applyHomeworkService.deleteModelResources(id, modelResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/saveDataResources/{id}",method = RequestMethod.PATCH)
    public JsonResult saveDataResources(@RequestBody DataResource dataResource, @PathVariable String id){
        applyHomeworkService.saveDataResources(id, dataResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/deleteDataResources/{id}",method = RequestMethod.PATCH)
    public JsonResult deleteDataResources(@RequestBody DataResource dataResource, @PathVariable String id){
        applyHomeworkService.deleteDataResources(id, dataResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/updateModelResources/{id}", method = RequestMethod.PATCH)
    public JsonResult updateModelResources(@RequestBody ModelResource modelResource,  @PathVariable String id) {
        applyHomeworkService.updateModelResources( id,modelResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/updateDataResources/{id}", method = RequestMethod.PATCH)
    public JsonResult updateDataResources(@RequestBody DataResource dataResource,  @PathVariable String id) {
        applyHomeworkService.updateDataResources( id,dataResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/addResearcher/{id}", method = RequestMethod.PATCH)
    public JsonResult addResearcher(@RequestBody Person person, @PathVariable String id) {
        applyHomeworkService.addResearcher(id, person);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/updateResearcher/{id}", method = RequestMethod.PATCH)
    public JsonResult updateResearcher(@RequestBody Person person, @PathVariable String id) {
        applyHomeworkService.updateResearcher( id,person);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteResearcher/{id}",method = RequestMethod.PATCH)
    public JsonResult deleteResearcher(@RequestBody Person person, @PathVariable String id){
        applyHomeworkService.deleteResearcher(id, person);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/addSummary/{id}", method = RequestMethod.PATCH)
    public JsonResult addSummary(@RequestBody Summary summary, @PathVariable String id) {
        applyHomeworkService.addSummary(id, summary);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteSummary/{id}",method = RequestMethod.PATCH)
    public JsonResult deleteSummary(@RequestBody Summary summary, @PathVariable String id){
        applyHomeworkService.deleteSummary(id, summary);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/saveComputeResources/{id}",method = RequestMethod.PATCH)
    public JsonResult saveComputeResources(@RequestBody ComputeResource computeResource, @PathVariable String id){
        applyHomeworkService.saveComputeResources(id, computeResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/deleteComputeResources/{id}",method = RequestMethod.PATCH)
    public JsonResult deleteComputeResources(@RequestBody ComputeResource computeResource, @PathVariable String id){
        applyHomeworkService.deleteComputeResources(id, computeResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/updateComputeResources/{id}", method = RequestMethod.PATCH)
    public JsonResult updateComputeResources(@RequestBody ComputeResource computeResource,  @PathVariable String id) {
        applyHomeworkService.updateComputeResources( id,computeResource);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/checkState/{uuid}", method = RequestMethod.GET)
    public JsonResult checkState(@PathVariable String uuid) {
        return ResultUtils.success(applyHomeworkService.checkState(uuid));
    }

    @AuthCheck
    @RequestMapping(value = "/clearTemp/{uuid}", method = RequestMethod.POST)
    public JsonResult clearTemp(@PathVariable String uuid) {
        applyHomeworkService.clearTemp(uuid);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam MultipartFile file, @RequestParam String uuid, @RequestParam String name) {
        applyHomeworkService.uploadFile(file, uuid, name);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergeBackgroundFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeBackgroundFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeBackgroundFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeBackgroundFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeBackgroundFile(@PathVariable String id) {
        applyHomeworkService.removeBackgroundFile(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergePurposeFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergePurposeFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergePurposeFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removePurposeFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removePurposeFile(@PathVariable String id) {
        applyHomeworkService.removePurposeFile(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergeSchemeFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeSchemeFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeSchemeFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeSchemeFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeSchemeFile(@PathVariable String id) {
        applyHomeworkService.removeSchemeFile(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergeResultValidationFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeResultValidationFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeResultValidationFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeResultValidationFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeResultValidationFile(@PathVariable String id) {
        applyHomeworkService.removeResultValidationFile(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergeResultVisualizationFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeResultVisualizationFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeResultVisualizationFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeResultVisualizationFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeResultVisualizationFile(@PathVariable String id) {
        applyHomeworkService.removeResultVisualizationFile(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/mergeConclusionFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeConclusionFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeConclusionFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeConclusionFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeConclusionFile(@PathVariable String id) {
        applyHomeworkService.removeConclusionFile(id);
        return ResultUtils.success();
    }

    @AuthCheck
    @RequestMapping(value = "/mergeProcessFiles/{id}", method = RequestMethod.POST)
    public JsonResult mergeProcessFile(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String uuid = jsonObject.getString("uuid");
        String name = jsonObject.getString("name");
        int total = jsonObject.getIntValue("total");
        return ResultUtils.success(applyHomeworkService.mergeProcessFiles(id, uuid, total, name));
    }
    @AuthCheck
    @RequestMapping(value = "/removeProcessFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeProcessFile(@PathVariable String id,@RequestBody Process process) {
        applyHomeworkService.removeProcessFile(id,process);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/removeTempVideoFile/{id}", method = RequestMethod.DELETE)
    public JsonResult removeTempVideoFile(@PathVariable String id,@RequestBody JSONObject jsonObject) {
        String uuid = jsonObject.getString("uuid");
        applyHomeworkService.removeTempVideoFile(id,uuid);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/getProcessLength/{id}", method = RequestMethod.GET)
    public JsonResult getProcessLength(@PathVariable String id) {
        applyHomeworkService.getProcessLength(id);
        return ResultUtils.success(applyHomeworkService.getProcessLength(id));
    }
    @AuthCheck
    @RequestMapping(value = "/deleteDataLocation/{id}", method = RequestMethod.POST)
    public JsonResult deleteDataLocation(@PathVariable String id,@RequestBody DataResource dataResource) {
        applyHomeworkService.deleteDataLocation(id,dataResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteModelcodeContent/{id}", method = RequestMethod.POST)
    public JsonResult deleteModelcodeContent(@PathVariable String id,@RequestBody ModelResource modelResource) {
        applyHomeworkService.deleteModelcodeContent(id,modelResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteModelStorage/{id}", method = RequestMethod.POST)
    public JsonResult deleteModelStorage(@PathVariable String id,@RequestBody ModelResource modelResource) {
        applyHomeworkService.deleteModelStorage(id,modelResource);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteresultValidationStorage/{id}", method = RequestMethod.POST)
    public JsonResult deleteresultValidationStorage(@PathVariable String id) {
        applyHomeworkService.deleteresultValidationStorage(id);
        return ResultUtils.success();
    }
    @AuthCheck
    @RequestMapping(value = "/deleteresultVisualizationStorage/{id}", method = RequestMethod.POST)
    public JsonResult deleteresultVisualizationStorage(@PathVariable String id) {
        applyHomeworkService.deleteresultVisualizationStorage(id);
        return ResultUtils.success();
    }
    @RequestMapping(value = "/download/{name}/{id}", method = RequestMethod.GET)
    public void download(@PathVariable String name,@PathVariable String id, HttpServletResponse response) {
        applyHomeworkService.download(name,id , response);
    }
}
