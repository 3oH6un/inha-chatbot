package kr.ac.itc.inhachatbot.exception;

/**
 * 잘못된 요청이 있을 때 발생하는 예외
 * <p>
 * 이 클래스는 잘못된 요청이 있을 때 발생하는 예외를 나타냅니다.
 * </p>
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * InvalidRequestException 생성자
     * <p>
     * 주어진 예외 메시지를 사용하여 InvalidRequestException 객체를 생성합니다.
     * </p>
     * @param message 예외 메시지
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}