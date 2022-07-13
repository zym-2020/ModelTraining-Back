package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:36
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseInfo {
    String name = "";
    String description = "";
    String location = "";
    String format = "";
    String version = "";
    String produceTime = "";
    String updateTime = "";
    String unit = "";
    String precision = "";
    String spaceTimeScale = "";
    String identifier = "";
}
