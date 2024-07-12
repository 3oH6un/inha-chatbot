package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import reactor.core.publisher.Mono;

/**
 * Chat 관련 서비스 인터페이스
 * <p>
 * 이 인터페이스는 유저의 메시지를 처리하고 FastAPI 서버로부터
 * 챗봇 응답을 수신하는 기능을 정의합니다.
 * </p>
 */
public interface ChatService {

    /**
     * User의 메시지를 통해 FastAPI 서버로부터 Chatbot 응답 수신
     * <p>
     * 주어진 방 UUID와 사용자의 메시지 데이터를 사용하여 FastAPI 서버로 요청을 보내고,
     * 챗봇의 응답 메시지를 반환합니다.
     * </p>
     * @param roomUuid 방 UUID
     * @param userMessageDTO 사용자의 MessageDTO
     * @return 생성된 Chatbot MessageDTO를 포함하는 Mono 객체
     */
    Mono<MessageDTO> processUserMessage(String roomUuid, MessageDTO userMessageDTO);
}