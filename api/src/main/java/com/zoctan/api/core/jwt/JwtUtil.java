package com.zoctan.api.core.jwt;

import com.zoctan.api.util.RSAUtil;
import com.zoctan.api.util.RedisUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.zoctan.api.core.ProjectConstant.ONLINE_USER_NUMBER;

/**
 * Json web token 工具
 * 验证、生成token
 *
 * @author Zoctan
 * @date 2018/5/27
 */
@Component
public class JwtUtil {
    private final static Logger log = LoggerFactory.getLogger(JwtUtil.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RSAUtil rsaUtil;
    @Resource
    private JWTSetting jwtSetting;

    private Claims getClaims(final String token) {
        final Jws<Claims> jws = this.parseToken(token);
        return jws == null ? null : jws.getBody();
    }

    /**
     * 根据token得到用户名
     */
    public String getUsername(final String token) {
        final Claims claims = this.getClaims(token);
        return claims == null ? null : claims.getSubject();
    }

    /**
     * 签发token
     *
     * @param username           用户名
     * @param grantedAuthorities 用户权限信息[ROLE_ADMIN, xx, ...]
     */
    public String sign(final String username, final Collection<? extends GrantedAuthority> grantedAuthorities) {
        // 看看缓存有没有用户 token
        final String token = (String) this.redisUtil.get(username);
        // 没有登录过
        if (token == null) {
            return this.createToken(username, grantedAuthorities);
        }
        final boolean isValidate = (boolean) this.redisUtil.get(token);
        // 有 token 但上次注销后被设置成了无效
        if (!isValidate) {
            return this.createToken(username, grantedAuthorities);
        } else {
            // 若仍有效，判断还有多长时间过期，如果 >= 2小时，发回该 token，否则重新签发
            final long expire = this.redisUtil.getExpire(token);
            final long defaultExpire = 2 * 3600;
            if (expire >= defaultExpire) {
                return token;
            } else {
                return this.createToken(username, grantedAuthorities);
            }
        }
    }

    /**
     * 清除用户在redis中的缓存
     *
     * @param username 用户名
     */
    @SuppressWarnings("unchecked")
    public void invalidRedisStore(final String username) {
        // 将 token 设置为无效
        final String token = (String) this.redisUtil.get(username);
        this.redisUtil.set(token, false);
        // 清除用户
        this.redisUtil.delete(username);
        // 当前用户数减1
        this.redisUtil.decrement(ONLINE_USER_NUMBER, 1);
        log.info("User<{}> logout and redis delete all info", username);
    }

    /**
     * 从请求头或请求参数中获取token
     */
    public String getTokenFromRequest(final HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader(this.jwtSetting.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getParameter(this.jwtSetting.getHeader());
        }
        return token;
    }

    /**
     * 返回用户认证
     */
    public UsernamePasswordAuthenticationToken getAuthentication(final String username, final String token) {
        // 解析 token 的 payload
        final Claims claims = this.getClaims(token);

        // 获取用户角色字符串
        // 将元素转换为 GrantedAuthority 接口集合
        //noinspection ConstantConditions
        final Collection<? extends GrantedAuthority> authorities =
                // 因为 JwtAuthenticationFilter 拦截器已经检查过 token 有效，所以可以忽略 get 空指针提示
                Arrays.stream(claims.get(this.jwtSetting.getAuthoritiesKey()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(
                new User(username, "", authorities),
                null,
                authorities);
    }

    /**
     * 验证token是否正确
     */
    public boolean validateToken(final String token) {
        boolean isValidate = true;
        try {
            isValidate = (boolean) this.redisUtil.get(token);
        } catch (final NullPointerException ignored) {
            // 可能 redis 部署出现了问题 || 清空了缓存导致 token 键不存在
        }
        // 能正确解析 token && redis 中缓存的 token 也是有效的
        return this.parseToken(token) != null && isValidate;
    }

    /**
     * 生成token
     */
    private String createToken(final String username, final Collection<? extends GrantedAuthority> grantedAuthorities) {
        // 获取用户的权限字符串，如 user:delete, role:add
        final String authorities = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.info("User<{}> : authorities => {}", username, authorities);

        final Date date = new Date(System.currentTimeMillis() + this.jwtSetting.getExpirationTime() * 1000);
        // 加载私钥
        final PrivateKey privateKey = this.rsaUtil.loadPemPrivateKey(this.jwtSetting.getPrivateKey());
        // 创建 token
        final String token = this.jwtSetting.getTokenPrefix() + " " +
                Jwts.builder()
                        // 设置用户名
                        .setSubject(username)
                        // 添加权限属性
                        .claim(this.jwtSetting.getAuthoritiesKey(), authorities)
                        // 设置失效时间
                        .setExpiration(date)
                        // 512位的私钥加密生成签名
                        .signWith(SignatureAlgorithm.RS256, privateKey)
                        .compact();
        // 保存用户 token
        // 因为用户注销后 JWT 本身只要没过期就仍然有效，所以只能通过 redis 缓存来校验有无效
        // 校验时只要 redis 中的 token 无效即可（JWT 本身可以校验有无过期，而 redis 过期即被删除了）
        // true 有效
        this.redisUtil.set(token, true, this.jwtSetting.getExpirationTime());
        // redis过期时间和 JWT 的一致
        this.redisUtil.set(username, token, this.jwtSetting.getExpirationTime());
        // 当前用户数加1
        this.redisUtil.increment(ONLINE_USER_NUMBER, 1);
        log.info("redis => put user<{}> token : {}", username, token);
        return token;
    }

    /**
     * 解析token
     */
    private Jws<Claims> parseToken(final String token) {
        try {
            // 加载公钥
            final PublicKey publicKey = this.rsaUtil.loadPemPublicKey(this.jwtSetting.getPublicKey());
            return Jwts
                    .parser()
                    // 公钥解密
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token.replace(this.jwtSetting.getTokenPrefix(), ""));
        } catch (final SignatureException e) {
            // 签名异常
            log.error("Invalid JWT signature");
        } catch (final MalformedJwtException e) {
            // JWT 格式错误
            log.error("Invalid JWT token");
        } catch (final ExpiredJwtException e) {
            // JWT 过期
            log.error("Expired JWT token");
        } catch (final UnsupportedJwtException e) {
            // 不支持该 JWT
            log.error("Unsupported JWT token");
        } catch (final IllegalArgumentException e) {
            // 参数错误异常
            log.error("JWT token compact of handler are invalid");
        }
        return null;
    }
}