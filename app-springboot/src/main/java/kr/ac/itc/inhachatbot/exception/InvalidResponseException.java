package kr.ac.itc.inhachatbot.exception;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import lombok.Getter;

/**
 * 잘못된 응답이 있을 때 발생하는 예외
 * <p>
 * 이 클래스는 잘못된 응답이 있을 때 발생하는 예외를 나타냅니다.
 * </p>
 */
@Getter
public class InvalidResponseException extends RuntimeException {
    private final MessageDTO messageDTO;
    /**
     * InvalidResponseException 생성자
     * <p>
     * 주어진 예외 메시지를 사용하여 InvalidResponseException 객체를 생성합니다.
     * </p>
     * @param message 예외 메시지
     */
    public InvalidResponseException(String message, MessageDTO messageDTO) {
        super(message);
        this.messageDTO = messageDTO;
    }
}