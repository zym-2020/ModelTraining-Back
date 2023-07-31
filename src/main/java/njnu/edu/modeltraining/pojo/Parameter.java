package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/14/11:21
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    String name;
    String description;
    String unit;
    String defaultValue;
    String format;
    DataResource dataResource;
}
