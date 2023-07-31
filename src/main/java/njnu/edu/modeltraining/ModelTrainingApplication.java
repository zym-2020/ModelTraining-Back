package njnu.edu.modeltraining;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("njnu.edu.modeltraining.dao")
public class ModelTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelTrainingApplication.class, args);
    }

}
