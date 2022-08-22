package njnu.edu.modeltraining.pojo.support.description;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.method.Space;
import njnu.edu.modeltraining.pojo.support.method.Spacetimescale;
import njnu.edu.modeltraining.pojo.support.method.Time;

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
    String purpose = "";
    String target = "";
    Time time = new Time();
    Space space = new Space();
    String method = "";
    List<String> pictures = new ArrayList<>();
    Video video = new Video();
}
