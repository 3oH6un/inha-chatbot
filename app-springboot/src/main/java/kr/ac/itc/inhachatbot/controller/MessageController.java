package kr.ac.itc.inhachatbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Message 관련 REST 컨트롤러
 * <p>
 * 이 컨트롤러는 방의 채팅 내역을 조회하는 기능을 제공합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/message")
@Tag(name = "Message", description = "Message 관련 API")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 방의 채팅 내역을 조회
     * <p>
     * 주어진 UUID를 통해 방의 채팅 내역을 조회하고, MessageDTO 리스트를 반환합니다.
     * </p>
     * @param uuid 방 UUID
     * @return MessageDTO 리스트를 포함하는 ResponseEntity
     */
    @Operation(summary = "방의 채팅 내역을 조회")
    @GetMapping("/{uuid}")
    public ResponseEntity<List<MessageDTO>> getChatHistory(@PathVariable String uuid) {
        List<MessageDTO> messages = messageService.getMessagesByRoomUuid(uuid);
        return ResponseEntity.ok(messages);
    }
}