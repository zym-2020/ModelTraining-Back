package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:35
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultOutput {
    String name = "";
    String description = "";
    String location = "";
    String format = "";
    String version = "";
    String time = "";
    String updateTime = "";
    String unit = "";
    String precision = "";
    String spaceTimeScale = "";
    String identifier = "";
    String visualization = "";
    String dataHandle = "";
    String dataTransformation = "";
}
