package njnu.edu.modeltraining.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:32
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "apply_homework")
public class ApplyHomework {
    @Id
    String id;
    Topic topic;
    String teamId;
    Integer state;
    Description description = new Description();
    Method method = new Method();
    Result result = new Result();
    Researcher researcher = new Researcher();
    List<Summary> summary = new ArrayList<>();
    Integer score = 0;
    Boolean ifSelect = false;
    String note;
}
