package sw.sustainable.springlabs.fpay.infrastructure.common;

import lombok.Builder;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private T data;
    private String message;

    @Builder
    private ApiResponse(String path, T data, String message) {
        this.path = path;
        this.data = data;
        this.message = message;
    }
}
