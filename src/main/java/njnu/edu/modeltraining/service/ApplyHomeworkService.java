package njnu.edu.modeltraining.service;

import njnu.edu.modeltraining.pojo.ApplyHomework;
import njnu.edu.modeltraining.pojo.support.Description;
import njnu.edu.modeltraining.pojo.support.Method;
import njnu.edu.modeltraining.pojo.support.Result;
import njnu.edu.modeltraining.pojo.support.Summary;
import njnu.edu.modeltraining.pojo.support.method.ComputeResource;
import njnu.edu.modeltraining.pojo.support.method.DataResource;
import njnu.edu.modeltraining.pojo.support.method.ModelResource;
import njnu.edu.modeltraining.pojo.support.method.Process;
import njnu.edu.modeltraining.pojo.support.researcher.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:46
 * @Description:
 */
public interface ApplyHomeworkService {
    ApplyHomework getByTeamId(String teamId);

    void saveDescription(String id, Description description);

    void saveProcess(String id, List<Process> processes);

    void saveResult(String id, Result result);

    void saveModelResources(String id, ModelResource modelResource);

    void deleteModelResources(String id, ModelResource modelResource);

    void saveDataResources(String id, DataResource datalResource);

    void deleteDataResources(String id, DataResource dataResource);

    void updateModelResources(String id,ModelResource modelResource);

    void updateDataResources(String id, DataResource dataResource);
    void addResearcher(String id, Person person);

    void updateResearcher(String id, Person person);

    void deleteResearcher(String id, Person person);

    void addSummary(String id, Summary summary);

    void deleteSummary(String id, Summary summary);

    void updateComputeResources(String id, ComputeResource computeResource);

    void deleteComputeResources(String id, ComputeResource computeResource);

    void saveComputeResources(String id, ComputeResource computeResource);

    void uploadFile(MultipartFile file, String uuid, String name);

    int checkState(String uuid);

    void clearTemp(String uuid);
    String mergeBackgroundFiles(String id,String uuid, int total, String name);
    void removeBackgroundFile(String id);
    String mergePurposeFiles(String id,String uuid, int total, String name);
    void removePurposeFile(String id);
    String mergeSchemeFiles(String id,String uuid, int total, String name);
    void removeSchemeFile(String id);
    String mergeResultValidationFiles(String id,String uuid, int total, String name);
    void removeResultValidationFile(String id);
    String mergeResultVisualizationFiles(String id,String uuid, int total, String name);
    void removeResultVisualizationFile(String id);
    String mergeConclusionFiles(String id,String uuid, int total, String name);
    void removeConclusionFile(String id);
    String mergeProcessFiles(String id,Process process,String uuid, int total, String name);
    void removeProcessFile(String id,Process process);

    int getProcessLength(String id);

    void deleteDataLocation(String id,DataResource dataResource);

    void deleteModelStorage(String id, ModelResource modelResource);

    void deleteresultValidationStorage(String id);

    void deleteresultVisualizationStorage(String id);

    void deleteModelcodeContent(String id, ModelResource modelResource);
}
