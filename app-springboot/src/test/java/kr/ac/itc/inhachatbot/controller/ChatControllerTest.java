package kr.ac.itc.inhachatbot.controller;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ChatController.class)
public class ChatControllerTest {

    // WebTestClient 인스턴스를 주입합니다.
    @Autowired
    private WebTestClient webTestClient;

    // ChatService 인스턴스를 모킹합니다.
    @MockBean
    private ChatService chatService;

    // 테스트용 사용자의 MessageDTO 객체를 설정합니다.
    private MessageDTO userMessageDTO;
    // 테스트용 챗봇의 MessageDTO 객체를 설정합니다.
    private MessageDTO chatbotMessageDTO;

    // 각 테스트 메서드 실행 전에 테스트용 MessageDTO 객체들을 설정합니다.
    @BeforeEach
    void setUp() {
        userMessageDTO = MessageDTO.builder().isUser(true).content("Hello").build();
        chatbotMessageDTO = MessageDTO.builder().isUser(false).content("Hello from chatbot").build();
    }

    // 정상적인 상황에서 유저의 메시지를 FastAPI 서버로 전송하고, 응답을 반환하는 테스트입니다.
    // ChatService의 processUserMessage 메서드가 성공적으로 응답하는 경우를 모킹합니다.
    // WebTestClient를 사용하여 POST 요청을 보내고, 응답의 상태 코드가 200 OK 인지, 응답 본문이 예상한 MessageDTO 인지 확인합니다.
    @Test
    void chat_success() {
        // Arrange
        when(chatService.processUserMessage(anyString(), any(MessageDTO.class))).thenReturn(Mono.just(chatbotMessageDTO));

        // Act & Assert
        webTestClient.post()
                .uri("/api/chat/{uuid}", "test-uuid")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userMessageDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MessageDTO.class)
                .value(response -> {
                    assert response != null;
                    assert "Hello from chatbot".equals(response.getContent());
                });
    }

    // FastAPI 서버로의 연결이 실패한 경우를 테스트합니다.
    // ChatService의 processUserMessage 메서드가 예외를 던지는 경우를 모킹합니다.
    // WebTestClient를 사용하여 POST 요청을 보내고, 응답의 상태 코드가 500 Internal Server Error 인지 확인합니다.
    @Test
    void chat_failure() {
        // Arrange
        when(chatService.processUserMessage(anyString(), any(MessageDTO.class))).thenReturn(Mono.error(new RuntimeException("Failed to process message")));

        // Act & Assert
        webTestClient.post()
                .uri("/api/chat/{uuid}", "test-uuid")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userMessageDTO)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}