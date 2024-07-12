package kr.ac.itc.inhachatbot.service;

import jakarta.transaction.Transactional;
import kr.ac.itc.inhachatbot.dto.ChatDTO;
import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.exception.InvalidResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Chat 관련 서비스 구현 클래스
 * <p>
 * 이 클래스는 Chat 관련 서비스를 구현합니다.
 * </p>
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private final WebClient webClient;
    private final MessageService messageService;

    @Autowired
    public ChatServiceImpl(WebClient webClient, MessageService messageService) {
        this.webClient = webClient;
        this.messageService = messageService;
    }

    @Override
    public Mono<MessageDTO> processUserMessage(String roomUuid, MessageDTO userMessageDTO) {
        MessageDTO userMessage = messageService.saveMessage(roomUuid, userMessageDTO);

        return webClient
                .post()
                .bodyValue(ChatDTO.builder()
                        .content(userMessage.getContent())
                        .build())
                .retrieve()
                .bodyToMono(ChatDTO.class)
                .flatMap(chatbotResponse -> {
                    MessageDTO chatbotMessage = messageService.saveMessage(roomUuid, MessageDTO.builder()
                            .isUser(false)
                            .content(chatbotResponse.getContent())
                            .createdTime(LocalDateTime.now())
                            .build());
                    return Mono.just(chatbotMessage);
                })
                .onErrorMap(e -> {
                    MessageDTO chatbotMessage = messageService.saveMessage(roomUuid, MessageDTO.builder()
                            .isUser(false)
                            .content("챗봇과의 통신 중 오류가 발생했습니다. 올바른 질문으로 다시 시도해주세요.")
                            .createdTime(LocalDateTime.now())
                            .build());
                    throw new InvalidResponseException("챗봇 서비스와의 통신 중 오류가 발생했습니다: " + e.getMessage(), chatbotMessage);
                });
    }
}