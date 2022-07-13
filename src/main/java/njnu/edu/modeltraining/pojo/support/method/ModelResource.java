package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResource {
    ModelBaseInfo modelBaseInfo = new ModelBaseInfo();
    ModelMetaData modelMetaData = new ModelMetaData();
    ModelSource modelSource = new ModelSource();
}
