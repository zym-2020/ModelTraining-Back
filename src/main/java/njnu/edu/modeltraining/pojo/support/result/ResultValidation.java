package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/14/11:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultValidation {
    String name = "";
    String description = "";
    String stepType = "";
    String operateType = "";
    String reference = "";
    String other = "";
    List<String> pictures = new ArrayList<>();
}
