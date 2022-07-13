package njnu.edu.modeltraining.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.Description;
import njnu.edu.modeltraining.pojo.support.Method;
import njnu.edu.modeltraining.pojo.support.Result;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    String teamId;
    Integer state;
    Description description = new Description();
    Method method = new Method();
    Result result = new Result();
}
