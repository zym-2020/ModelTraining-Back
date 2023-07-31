package njnu.edu.modeltraining.pojo.support.researcher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    String personId;
    String name;
    String surname;
    String institution;
    String address;
    String appellation;
    String email;
}
