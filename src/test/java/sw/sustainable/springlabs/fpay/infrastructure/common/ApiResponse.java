package sw.sustainable.springlabs.fpay.infrastructure.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private String status;
    private T body;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse(String status, T body) {
        this.status = status;
        this.body = body;
    }
}
