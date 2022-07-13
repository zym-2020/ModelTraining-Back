package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    String name;
    String description;
    String stepType;
    String operateType;
    String reference;
}
