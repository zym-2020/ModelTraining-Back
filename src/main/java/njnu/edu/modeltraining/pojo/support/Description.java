package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.description.Background;
import njnu.edu.modeltraining.pojo.support.description.Purpose;
import njnu.edu.modeltraining.pojo.support.description.Scheme;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:08
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Description {
    Background background = new Background();
    Purpose purpose = new Purpose();
    Scheme scheme = new Scheme();
}
