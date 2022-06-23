package njnu.edu.modeltraining.common.exception;

import lombok.Data;
import njnu.edu.modeltraining.common.result.ResultEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/15:59
 * @Description:
 */
@Data
public class MyException extends RuntimeException{
    Integer code;
    String msg;

    public MyException() {
        super();
    }

    public MyException(ResultEnum resultEnum) {
        super();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public MyException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
