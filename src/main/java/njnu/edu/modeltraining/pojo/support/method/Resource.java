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
 * @Date: 2022/07/13/14:23
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    List<ModelResource> modelResources = new ArrayList<>();
    List<DataResource> dataResources = new ArrayList<>();
    List<ComputeResource> computeResources = new ArrayList<>();
}
