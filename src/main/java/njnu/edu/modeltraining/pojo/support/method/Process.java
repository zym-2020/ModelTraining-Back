package njnu.edu.modeltraining.pojo.support.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.modeltraining.pojo.support.description.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/13/15:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    String name;
    String description;
    String stepType;
    String operateType;
    List<String> references;
    String other;
    List<String> pictures;
    Video video = new Video();
    ModelResource modelResource = new ModelResource();
    List<DataResource> dataResources = new ArrayList<>();
    Integer processScore;
}
