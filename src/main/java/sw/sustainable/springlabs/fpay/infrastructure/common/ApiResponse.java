package sw.sustainable.springlabs.fpay.infrastructure.common;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String path;
    private T data;

    public ApiResponse(String message, String path, T body) {
        this.message = message;
        this.path = path;
        this.data = body;
    }

    public static <T> ApiResponse<T> success(String path, T data) {
        return new ApiResponse<>("SUCCESS", path, data);
    }

}
