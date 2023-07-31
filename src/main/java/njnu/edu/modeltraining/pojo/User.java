package njnu.edu.modeltraining.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:24
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String email;
    String name;
    String password;
}
