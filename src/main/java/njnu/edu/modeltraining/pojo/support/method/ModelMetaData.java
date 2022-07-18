package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:29
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelMetaData {
    String hypothesis;
    String algorithm;
    List<ModelInput> modelInputs;
    List<ModelOutput> modelOutputs;
    String parameter;
    String iterate;
    String unit;
}
