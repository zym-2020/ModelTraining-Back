package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/14/11:23
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelOutput {
    String name;
    String description;
    String unit;
    String defaultValue;
    String format;
    DataResource dataResource;
}
