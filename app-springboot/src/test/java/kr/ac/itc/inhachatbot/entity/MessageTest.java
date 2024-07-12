package kr.ac.itc.inhachatbot.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {

    // Message 엔티티를 빌더를 사용하여 생성합니다.
    // 생성된 Message 객체의 필드가 예상한 대로 설정되었는지 검증합니다.
    @Test
    void testMessageBuilder() {
        // Arrange
        Room room = new Room();
        String content = "Hello, world!";
        Boolean isUser = true;

        // Act
        Message message = Message.builder()
                .room(room)
                .isUser(isUser)
                .content(content)
                .build();

        // Assert
        assertThat(message.getRoom()).isEqualTo(room);
        assertThat(message.getIsUser()).isEqualTo(isUser);
        assertThat(message.getContent()).isEqualTo(content);
    }

    // 기본 생성자를 사용하여 Message 엔티티를 생성합니다.
    // 생성된 Message 객체의 필드가 모두 null인지 검증합니다.
    @Test
    void testMessageDefaultConstructor() {
        // Act
        Message message = new Message();

        // Assert
        assertThat(message.getRoom()).isNull();
        assertThat(message.getIsUser()).isNull();
        assertThat(message.getContent()).isNull();
    }
}