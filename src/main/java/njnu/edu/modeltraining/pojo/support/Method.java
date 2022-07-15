package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.method.ComputeResource;
import njnu.edu.modeltraining.pojo.support.method.DataResource;
import njnu.edu.modeltraining.pojo.support.method.ModelResource;
import njnu.edu.modeltraining.pojo.support.method.Process;
import njnu.edu.modeltraining.pojo.support.method.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:20
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Method {
    Resource resource = new Resource();
    List<Process> processes = new ArrayList<>();
}
