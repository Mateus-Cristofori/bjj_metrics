package exception.handler;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import exception.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> defaultHandlerException(
        Exception exception,
        HttpStatus httpStatus,
        HttpServletRequest request,
        Map<String, String> fields
    ) {
        ErrorResponse error = new ErrorResponse(
            exception.getMessage(),
            httpStatus.value(),
            request.getRequestURI(),
            fields,
            Instant.now()
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(
        BaseException exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(
            exception,
            exception.getStatus(),
            request,
            exception.getFields()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception,
        HttpServletRequest request
    ) {
        Map<String, String> fields = new HashMap<>();
        exception
            .getBindingResult()
            .getFieldErrors()
            .forEach(error -> fields.put(error.getField(), error.getDefaultMessage()));
        return defaultHandlerException(
            new RuntimeException("Validation failed."),
            HttpStatus.BAD_REQUEST,
            request,
            fields
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
        Exception exception,
        HttpServletRequest request
    ) {
        return defaultHandlerException(
            exception,
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
            Collections.emptyMap()
        );
    }
}
