package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    String name;
    String description;
    String type;
    String location;
    String format;
    String parameter;
    String version;
    String produceTime;
    String updateTime;
    Time time;
    Space space;
    String isUpload = "false";
}
