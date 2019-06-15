package kim.jaehoon.dsmforest.service;

import kim.jaehoon.dsmforest.common.Exception.ConflictException;
import kim.jaehoon.dsmforest.common.Exception.UnauthorizedException;
import kim.jaehoon.dsmforest.common.response.JwtToken;
import kim.jaehoon.dsmforest.common.security.jwt.Jwt;
import kim.jaehoon.dsmforest.domain.Admin;
import kim.jaehoon.dsmforest.dto.SigninDTO;
import kim.jaehoon.dsmforest.dto.SignupDTO;
import kim.jaehoon.dsmforest.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service("auth-service")
@Slf4j
public class RealAuthService implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private Jwt jwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${jwt.prefix}")
    private String prefix;

    @Transactional
    public Admin signup(@Valid @RequestBody SignupDTO dto) {
        if (adminRepository.existsById(dto.getEmail())) {
            throw new ConflictException("email", "이메일 중복 감지");
        }
        Admin admin = dto.toAdmin(passwordEncoder());
        adminRepository.save(admin);
        return admin;
    }

    public Optional<Admin> validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(prefix)) {
            log.debug("Token Validation Failed. authorizationHeader: {}", authorizationHeader);
            return Optional.empty();
        }
        try {
            String token = authorizationHeader.substring(prefix.length());
            log.debug("After Substring Token : {}", token);
            String adminId = jwt.getAdminId(token);
            log.debug("After Verifying. userId : {}", adminId);
            Optional<Admin> admin = adminRepository.findById(adminId);
            log.debug("User present : {}", admin.isPresent());
            return admin;
        } catch (Exception e) {
            log.debug("Exception has occurred. {}", e);
            return Optional.empty();
        }
    }

    public JwtToken signin(SigninDTO dto) throws Exception {
        return jwt.createToken(adminRepository.findById(dto.getAdminId())
                .filter(admin -> admin.matchPassword(dto.getPassword(), passwordEncoder()))
                .orElseThrow(UnauthorizedException::new));
    }
}
