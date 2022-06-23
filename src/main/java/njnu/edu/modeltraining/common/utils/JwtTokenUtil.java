package njnu.edu.modeltraining.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import njnu.edu.modeltraining.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/06/23/16:09
 * @Description:
 */
public class JwtTokenUtil {
    private static String secret = "model-training";
    private static Long expiration = 345600l;
    private static Long failExpiration = 259200l;

    /**
     * @Description:负责生成token
     * @Author: Yiming
     * @Date: 2022/3/16
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        Date date = new Date(System.currentTimeMillis() + expiration * 1000);
        return date;
    }

    /**
     * @Description:获取token信息负载
     * @Author: Yiming
     * @Date: 2022/3/16
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * @Description:判断token刷新还是彻底过期,0为彻底过期，1为刷新token
     * @Author: Yiming
     * @Date: 2022/3/16
     */

    public static int tokenStatus(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims == null) {
            throw new MyException(ResultEnum.TOKEN_WRONG);
        }
        Date expiration = claims.getExpiration();
        if(new Date(expiration.getTime() + failExpiration  * 1000).before(new Date())) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * @Description:验证token是否失效
     * 失效返回true
     * @Author: Yiming
     * @Date: 2022/3/16
     */
    public static boolean validateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims == null) {
            throw new MyException(ResultEnum.TOKEN_WRONG);
        }
        return claims.getExpiration().before(new Date());
    }

    /**
     * @Description:根据用户信息生成token
     * @Author: Yiming
     * @Date: 2022/3/16
     */
    public static String generateTokenByUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("account", user.getAccount());

        return generateToken(claims);

    }

    /**
     * @Description:根据token获取用户信息
     * @Author: Yiming
     * @Date: 2022/3/16
     */
    public static Object getUserInfoByToken(String key, String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims == null) {
            throw new MyException(ResultEnum.TOKEN_WRONG);
        }
        return claims.get(key);
    }

    /**
     * @Description:刷新token
     * @Author: Yiming
     * @Date: 2022/3/17
     */

    public static String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims == null) {
            throw new MyException(ResultEnum.TOKEN_WRONG);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", claims.get("id"));
        map.put("name", claims.get("name"));
        map.put("email", claims.get("email"));
        map.put("roles", claims.get("roles"));
        return generateToken(map);
    }
}
