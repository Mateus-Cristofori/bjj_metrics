package exception.model;

import java.time.Instant;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer statusCode;
    private String path;
    private Map<String, String> fields;
    private Instant timestamp;
}
