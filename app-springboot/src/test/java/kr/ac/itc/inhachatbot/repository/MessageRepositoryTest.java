package kr.ac.itc.inhachatbot.repository;

import kr.ac.itc.inhachatbot.entity.Message;
import kr.ac.itc.inhachatbot.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageRepositoryTest {

    // MessageRepository 인스턴스를 자동 주입합니다.
    @Autowired
    private MessageRepository messageRepository;

    // RoomRepository 인스턴스를 자동 주입합니다.
    @Autowired
    private RoomRepository roomRepository;

    // 테스트용 Room 객체를 저장합니다.
    private Room testRoom;

    // 각 테스트 메서드 실행 전에 테스트용 Room 객체를 저장합니다.
    @BeforeEach
    void setUp() {
        testRoom = Room.builder().build();
        roomRepository.save(testRoom);
    }

    // 테스트용 Room 객체에 속한 메시지를 저장하고, findByRoom 메서드를 사용하여 메시지를 조회합니다.
    // 조회된 메시지 리스트의 크기와 내용이 예상한 대로 일치하는지 검증합니다.
    @Test
    void testFindByRoom() {
        // Arrange
        Message message1 = Message.builder().room(testRoom).isUser(true).content("Hello").build();
        Message message2 = Message.builder().room(testRoom).isUser(false).content("Hi there!").build();
        messageRepository.save(message1);
        messageRepository.save(message2);

        // Act
        List<Message> messages = messageRepository.findByRoom(testRoom);

        // Assert
        assertThat(messages).hasSize(2);
        assertThat(messages).extracting("content").containsExactlyInAnyOrder("Hello", "Hi there!");
    }
}