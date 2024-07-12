package kr.ac.itc.inhachatbot.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private int status;

    @Builder
    public ExceptionResponse(LocalDateTime timestamp, String message, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }
}
