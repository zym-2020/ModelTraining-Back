package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class ModelBaseInfo {
    String name="";
    String content="";
    String description="";
    String version="";
    String type="";
    String softDemand="";
    String softVersion="";
    String storage="";
    String serverStorage="";
    String algorithm="";
    String codeContent="";
    String language="";
    String dependent="";
    RefSystemTime refSystemTime = new RefSystemTime();
    RefSystemSpace refSystemSpace = new RefSystemSpace();
    String producteTime="";
    String hypothesis = "";
    String application = "";
    String updateTime="";
    String other="";
    String isCodeUpload = "false";
    String isUpload = "false";
}
