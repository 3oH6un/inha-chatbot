package kr.ac.itc.inhachatbot.exception;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidResponseExceptionTest {

    // InvalidResponseException 객체를 생성하고, 생성된 예외 객체의 메시지가 예상한 대로 설정되었는지 검증합니다.
    @Test
    void testInvalidResponseException() {
        // Arrange
        String errorMessage = "Invalid response error";
        MessageDTO messageDTO = MessageDTO.builder()
                .isUser(false)
                .content("챗봇과의 통신 중 오류가 발생했습니다. 다시 시도해주세요.")
                .createdTime(LocalDateTime.now())
                .build();

        // Act
        InvalidResponseException exception = new InvalidResponseException(errorMessage, messageDTO);

        // Assert
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getMessageDTO()).isEqualTo(messageDTO);
    }

    // InvalidResponseException이 던져질 때, 예외가 예상한 메시지를 포함하고 있는지 검증합니다.
    @Test
    void testInvalidResponseExceptionThrown() {
        // Arrange
        String errorMessage = "Invalid response error";
        MessageDTO messageDTO = MessageDTO.builder()
                .isUser(false)
                .content("챗봇과의 통신 중 오류가 발생했습니다. 올바른 질문으로 다시 시도해주세요.")
                .createdTime(LocalDateTime.now())
                .build();

        // Act & Assert
        InvalidResponseException exception = assertThrows(InvalidResponseException.class, () -> {
            throw new InvalidResponseException(errorMessage, messageDTO);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getMessageDTO()).isEqualTo(messageDTO);
    }
}