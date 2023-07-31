package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.method.Spacetimescale;

import java.util.ArrayList;
import java.util.List;

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
    String time = "";
    String storage = "";
    String format = "";
    String isUpload = "false";
}
