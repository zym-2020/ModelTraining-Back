package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.result.ResultOutput;

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
    ResultOutput resultOutput = new ResultOutput();
}
