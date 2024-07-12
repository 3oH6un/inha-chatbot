package kr.ac.itc.inhachatbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Chat 관련 REST 컨트롤러
 * <p>
 * 이 컨트롤러는 유저의 채팅 메시지를 처리하고, FastAPI 서버로 전송한 후
 * 챗봇의 응답을 받아 반환하는 역할을 합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat", description = "Chat 관련 API")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 유저의 채팅을 Fastapi 서버로 전송 후 응답 반환
     * <p>
     * 사용자가 보낸 메시지를 FastAPI 서버로 전송하고, 챗봇의 응답을 받아 반환합니다.
     * </p>
     * @param uuid 방 UUID
     * @param messageDTO 사용자의 MessageDTO
     * @return 생성된 Chatbot MessageDTO를 포함하는 Mono<ResponseEntity<MessageDTO>>
     */
    @Operation(summary = "유저의 채팅을 Fastapi 서버로 전송 후 응답 반환")
    @PostMapping("/{uuid}")
    public Mono<ResponseEntity<MessageDTO>> chat(@PathVariable String uuid, @RequestBody MessageDTO messageDTO) {
        return chatService.processUserMessage(uuid, messageDTO)
                .map(ResponseEntity::ok);
    }
}