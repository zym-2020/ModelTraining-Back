package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/14:34
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResource {
    String dataId;
    DataBaseInfo dataBaseInfo;
    DataActive dataActive;
    DataSource dataSource;
}
