package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.RoomDTO;
import kr.ac.itc.inhachatbot.entity.Room;

import java.util.Optional;

/**
 * Room 관련 서비스 인터페이스
 * <p>
 * 이 인터페이스는 Room 관련 서비스의 계약을 정의합니다.
 * </p>
 */
public interface RoomService {

    /**
     * 새로운 Room을 생성
     * <p>
     * 새로운 Room을 생성하고 그 정보를 RoomDTO로 반환합니다.
     * </p>
     * @return 생성된 RoomDTO
     */
    RoomDTO createRoom();

    /**
     * UUID로 Room을 조회
     * <p>
     * 주어진 방 UUID에 해당하는 Room을 조회하고, 그 정보를 RoomDTO로 반환합니다.
     * </p>
     * @param uuid 방 UUID
     * @return RoomDTO를 포함한 Optional 객체
     */
    Optional<RoomDTO> getRoomByUuid(String uuid);

    /**
     * 존재하는 Room을 조회하거나 새로 생성
     * <p>
     * 주어진 방 UUID에 해당하는 Room을 조회하거나, 방이 존재하지 않는 경우 새로운 Room을 생성합니다.
     * </p>
     * @param roomUuid 방 UUID
     * @return RoomDTO
     */
    RoomDTO getOrCreateRoom(String roomUuid);

    /**
     * UUID로 Room 엔티티 조회
     * <p>
     * 주어진 방 UUID에 해당하는 Room 엔티티를 조회합니다.
     * </p>
     * @param uuid 방 UUID
     * @return Room 엔티티
     */
    Room getRoomEntityByUuid(String uuid);
}