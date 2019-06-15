package kim.jaehoon.dsmforest.common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN, reason="cannot find match admin info.")
public class UnauthorizedException extends RuntimeException {
}
