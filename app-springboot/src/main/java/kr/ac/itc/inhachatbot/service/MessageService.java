package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.MessageDTO;

import java.util.List;

/**
 * Message 관련 서비스 인터페이스
 * <p>
 * 이 인터페이스는 Message 관련 서비스의 계약을 정의합니다.
 * </p>
 */
public interface MessageService {

    /**
     * Room UUID로 모든 메시지를 조회
     * <p>
     * 주어진 방 UUID에 해당하는 모든 메시지를 조회하고, 그 정보를 MessageDTO 리스트로 반환합니다.
     * </p>
     * @param roomUuid 방 UUID
     * @return MessageDTO 리스트
     */
    List<MessageDTO> getMessagesByRoomUuid(String roomUuid);

    /**
     * 특정 Room에 메시지 저장
     * <p>
     * 주어진 방 UUID와 메시지 데이터를 사용하여 해당 Room에 메시지를 저장하고, 저장된 메시지 정보를 MessageDTO로 반환합니다.
     * </p>
     * @param roomUuid   방 UUID
     * @param messageDTO 저장할 MessageDTO
     * @return 저장된 MessageDTO
     */
    MessageDTO saveMessage(String roomUuid, MessageDTO messageDTO);
}