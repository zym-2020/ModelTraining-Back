package njnu.edu.modeltraining.pojo.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    Description description = new Description();
    Method method = new Method();
    Result result = new Result();
    Researcher researcher = new Researcher();
}
