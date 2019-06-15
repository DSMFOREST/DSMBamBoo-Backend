package kim.jaehoon.dsmforest.common.security.jwt;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import kim.jaehoon.dsmforest.common.response.JwtToken;
import kim.jaehoon.dsmforest.domain.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Jwt {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.clientId}")
    private String clientId;

    @Value("${jwt.exp}")
    private int exp;

    @Value("${jwt.refreshExp}")
    private int refreshExp;

    @Value("${jwt.secret}")
    private String secret;

    private JWTSigner jwtSigner;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void initPropertiesSetting() {
        jwtSigner = new JWTSigner(Base64.decodeBase64(secret));
        jwtVerifier = new JWTVerifier(Base64.decodeBase64(secret), clientId, issuer);
    }

    private JWTSigner.Options initSetting(Integer exp) {
        JWTSigner.Options options = new JWTSigner.Options();
        options.setAlgorithm(Algorithm.HS512);
        options.setJwtId(true);
        options.setIssuedAt(true);
        options.setExpirySeconds(exp);
        return options;
    }

    public JwtToken createToken(Admin admin) throws Exception {
        String accessToken = createToken(admin.getAdminId());
        return new JwtToken(admin.getEmail(), accessToken, createRefreshToken(accessToken));
    }

    private String createToken(String adminId) {
        Map<String, Object> map = new HashMap<>();
        map.put("iss", issuer);
        map.put("userId", adminId);
        return jwtSigner.sign(map, initSetting(exp));
    }

    public String createRefreshToken(String token) throws Exception {
        Map<String, Object> claims = authToken(token);
        claims.remove("exp");
        claims.remove("iat");
        claims.remove("jti");
        return jwtSigner.sign(claims, initSetting(refreshExp));
    }

    public Map<String, Object> authToken(String token) throws Exception {
        return jwtVerifier.verify(token);
    }

    public boolean validation(String token) {
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAdminId(String token) throws Exception {
        log.debug("jwtVerifier verifying token [{}]", token);
        String adminId = (String) jwtVerifier.verify(token).get("adminId");
        log.debug("Verifying succeed. AdminId : {}", adminId);
        return adminId;
    }
}
