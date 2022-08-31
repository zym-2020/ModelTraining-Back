package njnu.edu.modeltraining.service.impl;

import lombok.SneakyThrows;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.LocalUpload;
import njnu.edu.modeltraining.dao.ApplyHomeworkRepository;
import njnu.edu.modeltraining.dao.UserRepository;
import njnu.edu.modeltraining.pojo.ApplyHomework;
import njnu.edu.modeltraining.pojo.Homework;
import njnu.edu.modeltraining.pojo.User;
import njnu.edu.modeltraining.pojo.support.*;
import njnu.edu.modeltraining.pojo.support.description.Video;
import njnu.edu.modeltraining.pojo.support.method.ComputeResource;
import njnu.edu.modeltraining.pojo.support.method.DataResource;
import njnu.edu.modeltraining.pojo.support.method.ModelResource;
import njnu.edu.modeltraining.pojo.support.method.Process;
import njnu.edu.modeltraining.pojo.support.researcher.Person;
import njnu.edu.modeltraining.service.ApplyHomeworkService;
import njnu.edu.modeltraining.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:47
 * @Description:
 */
@Service
public class ApplyHomeworkServiceImpl implements ApplyHomeworkService {
    @Value("${tempAddress}")
    String tempAddress;

    @Value("${videoAddress}")
    String videoAddress;

    @Value("${homeworkAddress}")
    String homeworkAddress;
    @Autowired
    RedisService redisService;

    @Autowired
    ApplyHomeworkRepository applyHomeworkRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public ApplyHomework getByTeamId(String teamId) {
        ApplyHomework applyHomework = applyHomeworkRepository.findByTeamId(teamId);
        if(applyHomework == null) {
            applyHomework = new ApplyHomework();
            applyHomework.setTeamId(teamId);
            applyHomework.setState(0);
            applyHomeworkRepository.save(applyHomework);
        }
        return applyHomework;
    }

    @Override
    public ApplyHomework getById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        User user = optionalUser.get();
        String teamId = user.getTeamId();
        ApplyHomework applyHomework = applyHomeworkRepository.findByTeamId(teamId);
        if(applyHomework == null) {
            applyHomework = new ApplyHomework();
            applyHomework.setTeamId(teamId);
            applyHomework.setState(0);
            applyHomeworkRepository.save(applyHomework);
        }
        return applyHomework;
    }

    @Override
    public void saveDescription(String id, Description description) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.setDescription(description);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void saveProcess(String id, List<Process> processes) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().setProcesses(processes);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void saveResult(String id, Result result) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.setResult(result);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void saveModelResources(String id, ModelResource modelResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getModelResources().add(modelResource);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteModelResources(String id, ModelResource modelResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getModelResources().remove(modelResource);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void saveDataResources(String id, DataResource dataResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getDataResources().add(dataResource);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteDataResources(String id, DataResource dataResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getDataResources().remove(dataResource);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void updateModelResources( String id,ModelResource modelResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<ModelResource> modelResources = applyHomework.getMethod().getResource().getModelResources();
        for(int i=0;i<modelResources.size();++i)
        {
            if(modelResources.get(i).getModelId().equals(modelResource.getModelId()))
            {
                modelResources.set(i,modelResource);
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void updateDataResources(String id, DataResource dataResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<DataResource> dataResources = applyHomework.getMethod().getResource().getDataResources();
        for(int i=0;i<dataResources.size();++i)
        {
            if(dataResources.get(i).getDataId().equals(dataResource.getDataId()))
            {
                dataResources.set(i,dataResource);
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public void addResearcher(String id, Person person) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getResearcher().getPersons().add(person);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void updateResearcher(String id, Person person) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<Person> persons = applyHomework.getResearcher().getPersons();
        for(int i=0;i<persons.size();++i)
        {
            if(persons.get(i).getPersonId().equals(person.getPersonId()))
            {
                persons.set(i,person);
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteResearcher(String id, Person person) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getResearcher().getPersons().remove(person);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void addSummary(String id, Summary summary) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getSummary().add(summary);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteSummary(String id, Summary summary) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getSummary().remove(summary);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void updateComputeResources(String id, ComputeResource computeResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<ComputeResource> computeResources = applyHomework.getMethod().getResource().getComputeResources();
        for(int i=0;i<computeResources.size();++i)
        {
            if(computeResources.get(i).getComputeId().equals(computeResource.getComputeId()))
            {
                computeResources.set(i,computeResource);
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteComputeResources(String id, ComputeResource computeResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getComputeResources().remove(computeResource);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void saveComputeResources(String id, ComputeResource computeResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getMethod().getResource().getComputeResources().add(computeResource);
        applyHomeworkRepository.save(applyHomework);
    }



    @Override
    public int checkState(String uuid) {
        Integer state = (Integer) redisService.get(uuid);
        if(state == null) {
            return -1;
        }
        if(state == 1 || state == -1) {
            redisService.del(uuid);
        }
        return state;
    }

    @Override
    public void clearTemp(String uuid) {
        String dir = tempAddress + uuid;
        File file = new File(dir);
        if(file.exists()) {
            LocalUpload.deleteFolder(dir);
        }
    }

    @Override
    public void uploadFile(MultipartFile file, String uuid, String name) {
        String dir = tempAddress + uuid;
        LocalUpload.UploadFile(file, name, dir);
    }

    @Override
    public String mergeBackgroundFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getDescription().getBackground().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeBackgroundFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getDescription().getBackground().getVideo().getId()+".mp4");
        if(file.exists()) {
            LocalUpload.deleteFolder(videoAddress + applyHomework.getDescription().getBackground().getVideo().getId()+".mp4");
        }
        applyHomework.getDescription().getBackground().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergePurposeFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getDescription().getPurpose().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removePurposeFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getDescription().getPurpose().getVideo().getId()+".mp4");
        if(file.exists()) {
            LocalUpload.deleteFolder(videoAddress + applyHomework.getDescription().getPurpose().getVideo().getId()+".mp4");
        }
        applyHomework.getDescription().getPurpose().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergeSchemeFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getDescription().getScheme().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeSchemeFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getDescription().getScheme().getVideo().getId()+".mp4");
        if(file.exists()) {

            LocalUpload.deleteFolder(videoAddress + applyHomework.getDescription().getScheme().getVideo().getId()+".mp4");
        }
        applyHomework.getDescription().getScheme().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergeResultValidationFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getResult().getResultValidation().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeResultValidationFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getResult().getResultValidation().getVideo().getId()+".mp4");
        if(file.exists()) {
            LocalUpload.deleteFolder(videoAddress + applyHomework.getResult().getResultValidation().getVideo().getId()+".mp4");
        }
        applyHomework.getResult().getResultValidation().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergeResultVisualizationFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getResult().getResultVisualization().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeResultVisualizationFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getResult().getResultVisualization().getVideo().getId()+".mp4");
        if(file.exists()) {
            LocalUpload.deleteFolder(videoAddress + applyHomework.getResult().getResultVisualization().getVideo().getId()+".mp4");
        }
        applyHomework.getResult().getResultVisualization().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergeConclusionFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                    Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
                    if(!optionalApplyHomework.isPresent()) {
                        throw new MyException(ResultEnum.NO_OBJECT);
                    }
                    ApplyHomework applyHomework = optionalApplyHomework.get();
                    applyHomework.getResult().getConclusion().setVideo(new Video(uuid,name));
                    applyHomeworkRepository.save(applyHomework);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeConclusionFile( String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        File file = new File(videoAddress + applyHomework.getResult().getConclusion().getVideo().getId()+".mp4");
        if(file.exists()) {

            LocalUpload.deleteFolder(videoAddress + applyHomework.getResult().getConclusion().getVideo().getId()+".mp4");
        }
        applyHomework.getResult().getConclusion().setVideo(new Video());
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public String mergeProcessFiles(String id, String uuid, int total, String name) {
        String uid = UUID.randomUUID().toString();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String from = tempAddress + uuid;
        String to = videoAddress + uuid + "." + suffix;        LocalUpload.deleteFolder(to);
        redisService.set(uid, 0, 60l);
        new Thread() {
            @Override
            @SneakyThrows
            public void run() {
                File file = new File(to);
                while (file.exists()) {
                    Thread.sleep(300);
                }
                int state = LocalUpload.merge(from, to, total);
                if(state == 1) {
                    redisService.set(uid, 1, 60l);
                } else {
                    redisService.set(uid, -1, 60l);
                }
                LocalUpload.deleteFolder(from);
            }
        }.start();
        return uid;
    }

    @Override
    public void removeProcessFile( String id,Process process) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<Process> processes = applyHomework.getMethod().getProcesses();
        int i;
        for(i=0;i<processes.size();++i)
        {
            if(processes.get(i).equals(process))
            {
                File file = new File(videoAddress + applyHomework.getMethod().getProcesses().get(i).getVideo().getId()+".mp4");
                if(file.exists()) {

                    LocalUpload.deleteFolder(videoAddress +applyHomework.getMethod().getProcesses().get(i).getVideo().getId()+".mp4");
                }
                applyHomework.getMethod().getProcesses().get(i).setVideo(new Video());
                applyHomeworkRepository.save(applyHomework);
                break;
            }
        }

    }
    @Override
    public void removeTempVideoFile(String id, String uuid) {
        File file = new File(videoAddress + uuid + ".mp4");
        if(file.exists()) {
            LocalUpload.deleteFolder(videoAddress + uuid +".mp4");
        }
    }

    @Override
    public void saveTopic(String id, Topic topic) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.setTopic(topic);
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public int getProcessLength(String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        return applyHomework.getMethod().getProcesses().size();
    }

    @Override
    public void deleteDataLocation(String id,DataResource dataResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<DataResource> dataResources = applyHomework.getMethod().getResource().getDataResources();
        int i;
        for( i=0;i<dataResources.size();++i)
        {
            if(dataResources.get(i).equals(dataResource))
            {
                applyHomework.getMethod().getResource().getDataResources().get(i).getDataBaseInfo().setLocation("");
                applyHomework.getMethod().getResource().getDataResources().get(i).getDataBaseInfo().setIsUpload("false");
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteModelStorage(String id, ModelResource modelResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<ModelResource> modelResources = applyHomework.getMethod().getResource().getModelResources();
        int i;
        for( i=0;i<modelResources.size();++i)
        {
            if(modelResources.get(i).equals(modelResource))
            {
                applyHomework.getMethod().getResource().getModelResources().get(i).getModelBaseInfo().setStorage("");
                applyHomework.getMethod().getResource().getModelResources().get(i).getModelBaseInfo().setIsUpload("false");
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }
    @Override
    public void deleteModelcodeContent(String id, ModelResource modelResource) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        List<ModelResource> modelResources = applyHomework.getMethod().getResource().getModelResources();
        int i;
        for( i=0;i<modelResources.size();++i)
        {
            if(modelResources.get(i).equals(modelResource))
            {
                applyHomework.getMethod().getResource().getModelResources().get(i).getModelBaseInfo().setCodeContent("");
                applyHomework.getMethod().getResource().getModelResources().get(i).getModelBaseInfo().setIsCodeUpload("false");
            }
        }
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void download(String name,String id, HttpServletResponse response) {
        String mName = name;
        String fileName = id + ".mp4";
        File file = new File(videoAddress + fileName );
        if(!file.exists()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        InputStream in = null;
        ServletOutputStream sos = null;
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(mName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            in = new FileInputStream(file);
            sos = response.getOutputStream();
            byte[] bytes = new byte[1024];
            while((in.read(bytes)) > -1) {
                sos.write(bytes);
            }
            sos.flush();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
    }

    @Override
    public void deleteresultValidationStorage(String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getResult().getResultValidation().getResultOutput().setStorage("");
        applyHomework.getResult().getResultValidation().getResultOutput().setIsUpload("false");
        applyHomeworkRepository.save(applyHomework);
    }

    @Override
    public void deleteresultVisualizationStorage(String id) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.getResult().getResultVisualization().getResultOutput().setStorage("");
        applyHomework.getResult().getResultVisualization().getResultOutput().setIsUpload("false");
        applyHomeworkRepository.save(applyHomework);
    }


}

