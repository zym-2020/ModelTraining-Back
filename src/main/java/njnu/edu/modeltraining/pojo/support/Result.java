package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.result.Conclusion;
import njnu.edu.modeltraining.pojo.support.result.Other;
import njnu.edu.modeltraining.pojo.support.result.ResultOutput;
import njnu.edu.modeltraining.pojo.support.result.ResultValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:06
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    ResultValidation resultValidation = new ResultValidation();
    ResultValidation resultVisualization  = new ResultValidation();
    Conclusion conclusion = new Conclusion();
}
