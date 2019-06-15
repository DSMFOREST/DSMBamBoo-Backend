package kim.jaehoon.dsmforest.service;

import kim.jaehoon.dsmforest.common.response.JwtToken;
import kim.jaehoon.dsmforest.domain.Admin;
import kim.jaehoon.dsmforest.dto.SigninDTO;
import kim.jaehoon.dsmforest.dto.SignupDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthService {

    Admin signup(@Valid @RequestBody SignupDTO dto);

    JwtToken signin(SigninDTO dto) throws Exception;
}
