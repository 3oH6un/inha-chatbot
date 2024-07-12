package kr.ac.itc.inhachatbot.dto;

import kr.ac.itc.inhachatbot.entity.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageDTOTest {

    // Message 엔티티를 생성하고, MessageDTO.of 메서드를 사용하여 MessageDTO 객체로 변환합니다.
    // 변환된 MessageDTO 객체의 필드가 원본 Message 엔티티와 동일한지 검증합니다.
    @Test
    void testOf() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Message message = Message.builder()
                .isUser(true)
                .content("Hello")
                .build();

        // Act
        MessageDTO messageDTO = MessageDTO.of(message);

        // Assert
        assertThat(messageDTO.getIsUser()).isEqualTo(true);
        assertThat(messageDTO.getContent()).isEqualTo("Hello");
    }

    // MessageDTO의 빌더를 사용하여 객체를 생성합니다.
    // 생성된 MessageDTO 객체의 필드가 예상한 대로 설정되었는지 검증합니다.
    @Test
    void testBuilder() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();

        // Act
        MessageDTO messageDTO = MessageDTO.builder()
                .isUser(true)
                .content("Hi")
                .createdTime(now)
                .build();

        // Assert
        assertThat(messageDTO.getIsUser()).isEqualTo(true);
        assertThat(messageDTO.getContent()).isEqualTo("Hi");
        assertThat(messageDTO.getCreatedTime()).isEqualTo(now);
    }
}