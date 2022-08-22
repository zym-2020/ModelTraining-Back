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
 * @Date: 2022/07/13/14:51
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputeResource {
    String computeId = "";
    ComputeBranch system = new ComputeBranch();
    ComputeBranch cpu = new ComputeBranch();
    ComputeBranch gpu = new ComputeBranch();
    ComputeBranch ram = new ComputeBranch();
    ComputeBranch hdd = new ComputeBranch();
    String other = "";
    List<ModelResource> modelResources = new ArrayList<>();
    List<DataResource> dataResources = new ArrayList<>();
}
