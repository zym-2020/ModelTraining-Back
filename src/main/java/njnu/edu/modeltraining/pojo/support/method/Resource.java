package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:23
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    ModelResource modelResource = new ModelResource();
    DataResource dataResource = new DataResource();
    ComputeResource computeResource = new ComputeResource();
}
