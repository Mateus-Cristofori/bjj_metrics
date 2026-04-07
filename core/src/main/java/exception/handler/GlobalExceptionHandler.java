package exception.handler;

import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.exceptions.InvalidPasswordException;
import com.bjj_metrics_brasil.exceptions.InvalidUserCredentialsException;
import com.bjj_metrics_brasil.exceptions.UserNotFoundException;
import exception.UnauthorizedException;
import exception.UnauthorizedUserException;
import exception.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> defaultHandlerException(
        Exception exception,
        HttpStatus httpStatus,
        HttpServletRequest request
    ) {
        ErrorResponse error = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.UNAUTHORIZED.value(),
            request.getRequestURI(),
            Instant.now()
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedUserException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(InvalidUserCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserCredentialsException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(exception, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(
            exception,
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        );
    }
}
