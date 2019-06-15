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
public class SignupDTO {
    @NotNull
    private String adminId;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public Admin toAdmin(PasswordEncoder passwordEncoder) {
        return new Admin(adminId, passwordEncoder.encode(password), email, firstName, lastName);
    }

    public void SigninDTO(@NotNull String adminId, @NotNull String password, @NotNull String email, @NotNull String firstName, @NotNull String lastName) {
        this.adminId = adminId;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
