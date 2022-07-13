package njnu.edu.modeltraining.pojo.support.description;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:17
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scheme {
    String target = "";
    String location = "";
    String time = "";
    String person = "";
    String method = "";
    List<String> pictures = new ArrayList<>();
    List<String> videos = new ArrayList<>();
}
