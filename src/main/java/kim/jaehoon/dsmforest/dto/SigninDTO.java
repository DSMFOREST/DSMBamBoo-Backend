package kim.jaehoon.dsmforest.dto;

import kim.jaehoon.dsmforest.domain.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SigninDTO {

    @NotNull
    private String adminId;

    @NotNull
    private String password;
}
