package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.entity.Message;
import kr.ac.itc.inhachatbot.entity.Room;
import kr.ac.itc.inhachatbot.exception.InvalidRequestException;
import kr.ac.itc.inhachatbot.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

    // MessageRepository 인스턴스를 모킹합니다.
    @Mock
    private MessageRepository messageRepository;

    // RoomService 인스턴스를 모킹합니다.
    @Mock
    private RoomService roomService;

    // MessageServiceImpl 인스턴스를 주입합니다.
    @InjectMocks
    private MessageServiceImpl messageService;

    // 테스트용 Room 객체를 설정합니다.
    private Room testRoom;
    // 테스트용 Message 객체를 설정합니다.
    private Message testMessage;
    // 테스트용 MessageDTO 객체를 설정합니다.
    private MessageDTO testMessageDTO;

    // 각 테스트 메서드 실행 전에 테스트용 Room 객체와 Message 객체 및 DTO를 설정합니다.
    @BeforeEach
    void setUp() {
        testRoom = Room.builder().build();
        testMessage = Message.builder()
                .room(testRoom)
                .isUser(true)
                .content("Hello")
                .build();
        testMessageDTO = MessageDTO.of(testMessage);
    }

    // 주어진 방 UUID에 해당하는 모든 메시지를 조회하는 기능을 테스트합니다.
    // roomService.getRoomEntityByUuid와 messageRepository.findByRoom 메서드의 동작을 모킹하여 설정합니다.
    // 조회된 메시지 리스트의 크기와 내용이 예상한 대로 일치하는지 검증합니다.
    @Test
    void getMessagesByRoomUuid() {
        // Arrange
        when(roomService.getRoomEntityByUuid(anyString())).thenReturn(testRoom);
        when(messageRepository.findByRoom(any(Room.class))).thenReturn(Stream.of(testMessage).collect(Collectors.toList()));

        // Act
        List<MessageDTO> messages = messageService.getMessagesByRoomUuid(testRoom.getRoomName());

        // Assert
        assertThat(messages).hasSize(1);
        assertThat(messages.get(0).getContent()).isEqualTo(testMessage.getContent());
    }

    // 주어진 방 UUID와 메시지 데이터를 사용하여 메시지를 저장하는 기능을 테스트합니다.
    // roomService.getRoomEntityByUuid와 messageRepository.save 메서드의 동작을 모킹하여 설정합니다.
    // 저장된 메시지의 내용이 예상한 대로 일치하는지 검증합니다.
    @Test
    void saveMessage_success() {
        // Arrange
        when(roomService.getRoomEntityByUuid(anyString())).thenReturn(testRoom);
        when(messageRepository.save(any(Message.class))).thenReturn(testMessage);

        // Act
        MessageDTO savedMessage = messageService.saveMessage(testRoom.getRoomName(), testMessageDTO);

        // Assert
        assertThat(savedMessage.getContent()).isEqualTo(testMessage.getContent());
    }

    // 메시지 내용이 비어있는 경우 예외가 발생하는지 테스트합니다.
    // InvalidRequestException 예외가 발생하고, 예외 메시지가 예상한 대로 포함되어 있는지 검증합니다.
    @Test
    void saveMessage_invalidRequest() {
        // Arrange
        testMessageDTO = MessageDTO.builder().isUser(true).content("").build();

        // Act & Assert
        assertThatThrownBy(() -> messageService.saveMessage(testRoom.getRoomName(), testMessageDTO))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessageContaining("메시지 내용은 비어 있을 수 없습니다.");
    }
}