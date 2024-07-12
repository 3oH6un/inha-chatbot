package kr.ac.itc.inhachatbot.repository;

import kr.ac.itc.inhachatbot.entity.Message;
import kr.ac.itc.inhachatbot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Message 엔티티를 위한 레포지토리
 * <p>
 * 이 인터페이스는 Message 엔티티에 대한 데이터베이스 작업을 처리합니다.
 * </p>
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Room으로 모든 메시지를 찾음
     * <p>
     * 주어진 Room 엔티티에 속한 모든 메시지를 반환합니다.
     * </p>
     * @param room Room 엔티티
     * @return 메시지 리스트
     */
    List<Message> findByRoom(Room room);
}