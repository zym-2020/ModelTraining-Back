package njnu.edu.modeltraining.pojo.support.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Other {
    String text = "";
    List<String> pictures = new ArrayList<>();
    List<String> videos = new ArrayList<>();
}
