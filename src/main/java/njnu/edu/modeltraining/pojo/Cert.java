package njnu.edu.modeltraining.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/07/31/20:43
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cert {
    String email;
    Integer count;
    String address;
}
