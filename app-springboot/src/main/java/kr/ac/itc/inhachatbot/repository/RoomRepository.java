package kr.ac.itc.inhachatbot.repository;

import kr.ac.itc.inhachatbot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Room 엔티티를 위한 레포지토리
 * <p>
 * 이 인터페이스는 Room 엔티티에 대한 데이터베이스 작업을 처리합니다.
 * </p>
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * roomName으로 Room을 찾음
     * <p>
     * 주어진 방 이름 (UUID)에 해당하는 Room 엔티티를 반환합니다.
     * </p>
     * @param roomName 방 이름 (UUID)
     * @return Room을 포함한 Optional 객체
     */
    Optional<Room> findByRoomName(String roomName);
}