package galo.app.web.handler;
import galo.app.dto.ApiError;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> illegal(IllegalStateException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(ex.getMessage(), OffsetDateTime.now()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError("Validation error", OffsetDateTime.now()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> any(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getMessage(), OffsetDateTime.now()));
    }
}
