package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.description.Video;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conclusion {
    String text = "";
    String paper = "";
    List<String> pictures = new ArrayList<>();
    Video video = new Video();
}
