package sw.sustainable.springlabs.fpay.infrastructure.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String message;
    private String path;
    private T data;

    public ApiResponse(String path, T data) {
        this.message = "SUCCESS";
        this.status = HttpStatus.OK;
        this.path = path;
        this.data = data;
    }

}
