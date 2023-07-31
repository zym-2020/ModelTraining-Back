package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:31
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelSource {
    List<String> references;
    String publication;
    String develop;
    String UId;
    String license;
}
