package kr.ac.itc.inhachatbot.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundExceptionTest {

    // NotFoundException 객체를 생성하고, 생성된 예외 객체의 메시지가 예상한 대로 설정되었는지 검증합니다.
    @Test
    void testNotFoundException() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        NotFoundException exception = new NotFoundException(errorMessage);

        // Assert
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    // NotFoundException이 던져질 때, 예외가 예상한 메시지를 포함하고 있는지 검증합니다.
    @Test
    void testNotFoundExceptionThrown() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(errorMessage);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }
}