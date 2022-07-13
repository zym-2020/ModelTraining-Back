package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelBaseInfo {
    String conception = "";
    String storage = "";
    String version = "";
    String unit = "";
    String range = "";
}
