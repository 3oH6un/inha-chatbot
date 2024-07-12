package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.ChatDTO;
import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.exception.InvalidResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatServiceImplTest {

    // WebClient 인스턴스를 모킹합니다.
    @Mock
    private WebClient webClient;

    // WebClient.RequestBodyUriSpec 인스턴스를 모킹합니다.
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    // WebClient.RequestBodySpec 인스턴스를 모킹합니다.
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    // WebClient.RequestHeadersSpec 인스턴스를 모킹합니다.
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    // WebClient.ResponseSpec 인스턴스를 모킹합니다.
    @Mock
    private WebClient.ResponseSpec responseSpec;

    // MessageService 인스턴스를 모킹합니다.
    @Mock
    private MessageService messageService;

    // ChatServiceImpl 인스턴스를 주입합니다.
    @InjectMocks
    private ChatServiceImpl chatService;

    // 테스트용 사용자의 MessageDTO 객체를 설정합니다.
    private MessageDTO userMessageDTO;
    // 테스트용 챗봇의 MessageDTO 객체를 설정합니다.
    private MessageDTO chatbotMessageDTO;

    // 각 테스트 메서드 실행 전에 테스트용 MessageDTO 객체들을 설정합니다.
    @BeforeEach
    void setUp() {
        userMessageDTO = MessageDTO.builder().isUser(true).content("Hello").build();
        chatbotMessageDTO = MessageDTO.builder().isUser(false).content("Hello from chatbot").build();

        // WebClient의 각 메서드 체이닝을 모킹합니다.
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }

    // 정상적인 상황에서 유저의 메시지를 FastAPI 서버로 전송하고, 응답을 반환하는 테스트입니다.
    // WebClient의 동작을 모킹하여 설정합니다.
    // StepVerifier를 사용하여 반환된 Mono 객체의 내용이 예상한 대로 일치하는지 검증합니다.
    @Test
    void processUserMessage_success() {
        // Arrange
        ChatDTO chatDTO = ChatDTO.builder().content("Hello from chatbot").build();

        when(responseSpec.bodyToMono(ChatDTO.class)).thenReturn(Mono.just(chatDTO));
        when(messageService.saveMessage(anyString(), any(MessageDTO.class)))
                .thenReturn(userMessageDTO)
                .thenReturn(chatbotMessageDTO);

        // Act
        Mono<MessageDTO> result = chatService.processUserMessage("test-room-uuid", userMessageDTO);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> response.getContent().equals("Hello from chatbot") && !response.getIsUser())
                .verifyComplete();
    }

    // FastAPI 서버로의 연결이 실패한 경우를 테스트합니다.
    // WebClient의 동작을 모킹하여 예외를 발생하도록 설정합니다.
    // StepVerifier를 사용하여 반환된 Mono 객체가 InvalidResponseException 예외를 포함하는지 검증합니다.
    @Test
    void processUserMessage_failure() {
        // Arrange
        when(responseSpec.bodyToMono(ChatDTO.class))
                .thenReturn(Mono.error(new WebClientResponseException("Failed to connect", 500, "Internal Server Error", null, null, null)));

        when(messageService.saveMessage(anyString(), any(MessageDTO.class)))
                .thenReturn(userMessageDTO);

        // Act
        Mono<MessageDTO> result = chatService.processUserMessage("test-room-uuid", userMessageDTO);

        // Assert
        StepVerifier.create(result)
                .expectError(InvalidResponseException.class)
                .verify();
    }
}