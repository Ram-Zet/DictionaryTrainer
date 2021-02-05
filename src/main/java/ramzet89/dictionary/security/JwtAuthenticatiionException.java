package ramzet89.dictionary.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticatiionException extends AuthenticationException {
    private HttpStatus httpStatus;




    public JwtAuthenticatiionException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public JwtAuthenticatiionException(String msg, Throwable t) {
        super(msg, t);
    }
}
