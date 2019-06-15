package kim.jaehoon.dsmforest.controller;

import kim.jaehoon.dsmforest.common.response.JwtToken;
import kim.jaehoon.dsmforest.common.response.RestResponse;
import kim.jaehoon.dsmforest.domain.Admin;
import kim.jaehoon.dsmforest.dto.SigninDTO;
import kim.jaehoon.dsmforest.dto.SignupDTO;
import kim.jaehoon.dsmforest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    @Qualifier("auth-service")
    private AuthService authService;

    @PostMapping("/signup")
    public RestResponse<Admin> signup(@Valid @RequestBody SignupDTO dto) {
        return RestResponse.success(authService.signup(dto));
    }

    @PostMapping("/signin")
    public RestResponse<JwtToken> signin(@Valid @RequestBody SigninDTO dto) throws Exception {
        return RestResponse.success(authService.signin(dto));
    }
}
