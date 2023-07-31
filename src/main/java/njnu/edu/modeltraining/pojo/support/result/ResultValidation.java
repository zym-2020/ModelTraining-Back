package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.description.Video;
import njnu.edu.modeltraining.pojo.support.method.DataResource;
import njnu.edu.modeltraining.pojo.support.method.ModelResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/14/11:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultValidation {
    String name = "";
    String description = "";
    String stepType = "";
    List<String> pictures = new ArrayList<>();
    Video video = new Video();
    DataResource dataResource = new DataResource();
    ResultOutput resultOutput = new ResultOutput();
    String softName = "";
    String softVersion = "";
    String softStorage = "";
    String softDepend = "";
    String codeContent = "";
    String codeLanguage = "";
    String codeDepend  = "";
}
