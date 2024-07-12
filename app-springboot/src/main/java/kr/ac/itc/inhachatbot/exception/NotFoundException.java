package kr.ac.itc.inhachatbot.exception;

/**
 * 특정 리소스를 찾을 수 없을 때 발생하는 예외
 * <p>
 * 이 클래스는 특정 리소스를 찾을 수 없을 때 발생하는 예외를 나타냅니다.
 * </p>
 */
public class NotFoundException extends RuntimeException {

    /**
     * NotFoundException 생성자
     * <p>
     * 주어진 예외 메시지를 사용하여 NotFoundException 객체를 생성합니다.
     * </p>
     * @param message 예외 메시지
     */
    public NotFoundException(String message) {
        super(message);
    }
}