package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:20
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    String type = "";
    String problem = "";
    String title = "";
}
