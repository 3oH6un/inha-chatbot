package kr.ac.itc.inhachatbot.exception;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * 글로벌 예외 처리기
 * <p>
 * 애플리케이션 전반에서 발생하는 예외를 처리합니다.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * NotFoundException을 처리합니다.
     *
     * @param ex 발생한 예외
     * @return 응답 엔티티
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        logger.error("NotFoundException: ", ex);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * InvalidRequestException을 처리합니다.
     *
     * @param ex 발생한 예외
     * @return 응답 엔티티
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException ex) {
        logger.error("InvalidRequestException: ", ex);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * InvalidResponseException을 처리합니다.
     *
     * @param ex 발생한 예외
     * @return 응답 엔티티
     */
    @ExceptionHandler(InvalidResponseException.class)
    public ResponseEntity<MessageDTO> handleInvalidResponseException(InvalidResponseException ex) {
        logger.error("InvalidResponseException: ", ex);
        MessageDTO messageDTO = ex.getMessageDTO();
        return new ResponseEntity<>(messageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * HttpRequestMethodNotSupportedException을 처리합니다.
     *
     * @param ex 발생한 예외
     * @return 응답 엔티티
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HttpRequestMethodNotSupportedException: ", ex);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 기타 모든 예외를 처리합니다.
     *
     * @param ex 발생한 예외
     * @return 응답 엔티티
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex) {
        logger.error("Exception: ", ex);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}