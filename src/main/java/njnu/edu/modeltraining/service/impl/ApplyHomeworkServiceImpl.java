package njnu.edu.modeltraining.service.impl;

import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.dao.ApplyHomeworkRepository;
import njnu.edu.modeltraining.pojo.ApplyHomework;
import njnu.edu.modeltraining.pojo.support.Description;
import njnu.edu.modeltraining.service.ApplyHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:47
 * @Description:
 */
@Service
public class ApplyHomeworkServiceImpl implements ApplyHomeworkService {

    @Autowired
    ApplyHomeworkRepository applyHomeworkRepository;

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
    public void saveDescription(String id, Description description) {
        Optional<ApplyHomework> optionalApplyHomework = applyHomeworkRepository.findById(id);
        if(!optionalApplyHomework.isPresent()) {
            throw new MyException(ResultEnum.NO_OBJECT);
        }
        ApplyHomework applyHomework = optionalApplyHomework.get();
        applyHomework.setDescription(description);
        applyHomeworkRepository.save(applyHomework);
    }
}
