package njnu.edu.modeltraining.common.auth;

import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.JsonResult;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.common.utils.JwtTokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/16:01
 * @Description:
 */
@Aspect
@Component
public class AuthCheckAspect {
    @Autowired
    HttpServletRequest request;

    private String tokenHead = "Bearer ";

    @Pointcut("@annotation(njnu.edu.modeltraining.common.auth.AuthCheck)")
    public void check() {};

    @Around("check()")
    public Object aroundCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        String authorization = request.getHeader("authorization");
        if(authorization != null) {
            String token = authorization.substring(tokenHead.length());
            if(JwtTokenUtil.validateToken(token)) {
                if(JwtTokenUtil.tokenStatus(token) == 1) {
                    JsonResult result = (JsonResult) joinPoint.proceed();
                    result.setRefreshToken(JwtTokenUtil.refreshToken(token));
                    return result;
                } else {
                    throw new MyException(ResultEnum.TOKEN_WRONG);
                }
            } else {
                return joinPoint.proceed();
            }
        } else {
            throw new MyException(ResultEnum.TOKEN_WRONG);
        }

    }
}
