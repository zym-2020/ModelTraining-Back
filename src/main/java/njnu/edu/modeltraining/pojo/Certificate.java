package njnu.edu.modeltraining.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/08/01/14:54
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "certificate")
public class Certificate {
    @Id
    String id;
    String userId;
    String type;
    String number;
}
