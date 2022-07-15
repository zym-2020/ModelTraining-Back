package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/14/10:42
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResource {
    String name;
    String dataInfo;
    String modelInfo;
    String type;
    List<ModelInput> modelInputs;
    List<ModelOutput> modelOutputs;
}
