package njnu.edu.modeltraining.common.resolver;

import com.alibaba.fastjson.JSON;
import njnu.edu.modeltraining.common.utils.JwtTokenUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/16:35
 * @Description:
 */
public class TokenResolver implements HandlerMethodArgumentResolver {
    public String tokenHead = "Bearer ";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtTokenParser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = (request.getHeader("authorization")).substring(this.tokenHead.length());
        JwtTokenParser jwtTokenParser = parameter.getParameterAnnotation(JwtTokenParser.class);
        Object temp = JwtTokenUtil.getUserInfoByToken(jwtTokenParser.value(), token);
        if(jwtTokenParser.value().equals("roles")) {
            return JSON.parseObject((String) temp, String[].class);
        }
        return JwtTokenUtil.getUserInfoByToken(jwtTokenParser.value(), token);
    }
}
