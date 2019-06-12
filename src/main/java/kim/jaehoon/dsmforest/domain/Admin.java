package kim.jaehoon.dsmforest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    private String adminId;

    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
