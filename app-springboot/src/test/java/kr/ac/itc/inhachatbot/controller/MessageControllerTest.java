package kr.ac.itc.inhachatbot.controller;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    // MessageService를 모킹합니다.
    @Mock
    private MessageService messageService;

    // MessageController 인스턴스를 생성하고, 의존성 주입을 설정합니다.
    @InjectMocks
    private MessageController messageController;

    // WebTestClient 인스턴스를 생성하여 REST API를 테스트합니다.
    private WebTestClient webTestClient;

    // 각 테스트 메서드 실행 전에 WebTestClient를 설정합니다.
    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(messageController).build();
    }

    // 정상적인 상황에서 방의 채팅 내역을 조회하는 테스트입니다.
    // messageService.getMessagesByRoomUuid가 호출될 때, 미리 정의된 메시지 리스트를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 /api/message/{uuid} 엔드포인트를 호출하고, 상태 코드가 200 OK인지, 반환된 메시지 리스트가 예상한 대로 일치하는지 확인합니다.
    @Test
    void getChatHistory_success() {
        // Arrange
        List<MessageDTO> messageList = new ArrayList<>();
        messageList.add(MessageDTO.builder()
                .isUser(true)
                .content("Hello")
                .build());
        messageList.add(MessageDTO.builder()
                .isUser(false)
                .content("Hi there!")
                .build());
        when(messageService.getMessagesByRoomUuid(anyString())).thenReturn(messageList);

        // Act & Assert
        webTestClient.get().uri("/api/message/{uuid}", "test-room-uuid")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MessageDTO.class)
                .isEqualTo(messageList);
    }

    // 채팅 내역이 없는 경우를 테스트합니다.
    // messageService.getMessagesByRoomUuid가 호출될 때, 빈 리스트를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 /api/message/{uuid} 엔드포인트를 호출하고, 상태 코드가 200 OK인지, 반환된 메시지 리스트가 빈 리스트인지 확인합니다.
    @Test
    void getChatHistory_noMessages() {
        // Arrange
        when(messageService.getMessagesByRoomUuid(anyString())).thenReturn(new ArrayList<>());

        // Act & Assert
        webTestClient.get().uri("/api/message/{uuid}", "test-room-uuid")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MessageDTO.class)
                .isEqualTo(new ArrayList<>());
    }
}