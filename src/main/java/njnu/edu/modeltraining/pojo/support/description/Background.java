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
 * @Date: 2022/07/13/14:10
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Background {
    String text = "";
    List<String> pictures = new ArrayList<>();
    Video video = new Video();
    List<String> references = new ArrayList<>();
}
