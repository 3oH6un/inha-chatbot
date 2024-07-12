package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.entity.Message;
import kr.ac.itc.inhachatbot.entity.Room;
import kr.ac.itc.inhachatbot.exception.InvalidRequestException;
import kr.ac.itc.inhachatbot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Message 관련 서비스 구현 클래스
 * <p>
 * 이 클래스는 Message 관련 서비스를 구현합니다.
 * </p>
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RoomService roomService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, RoomService roomService) {
        this.messageRepository = messageRepository;
        this.roomService = roomService;
    }

    @Override
    public List<MessageDTO> getMessagesByRoomUuid(String roomUuid) {
        Room room = roomService.getRoomEntityByUuid(roomUuid);
        List<Message> messages = messageRepository.findByRoom(room);
        return messages.stream()
                .map(MessageDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO saveMessage(String roomUuid, MessageDTO messageDTO) {
        if (messageDTO.getContent() == null || messageDTO.getContent().isEmpty()) {
            throw new InvalidRequestException("메시지 내용은 비어 있을 수 없습니다.");
        }
        Room room = roomService.getRoomEntityByUuid(roomUuid);
        Message message = Message.builder()
                .room(room)
                .isUser(messageDTO.getIsUser())
                .content(messageDTO.getContent())
                .build();
        Message savedMessage = messageRepository.save(message);
        return MessageDTO.of(savedMessage);
    }
}