package exception;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedUserException extends BaseException {

    public UnauthorizedUserException() {
        super("User unauthorized.", HttpStatus.UNAUTHORIZED);
    }
}
