package kr.ac.itc.inhachatbot.repository;

import kr.ac.itc.inhachatbot.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoomRepositoryTest {

    // RoomRepository 인스턴스를 자동 주입합니다.
    @Autowired
    private RoomRepository roomRepository;

    // 테스트용 Room 객체를 저장합니다.
    private Room testRoom;

    // 각 테스트 메서드 실행 전에 테스트용 Room 객체를 저장합니다.
    @BeforeEach
    void setUp() {
        testRoom = Room.builder().build();
        roomRepository.save(testRoom);
    }

    // Room 엔티티를 저장하고, findByRoomName 메서드를 사용하여 방 이름으로 조회합니다.
    // 조회된 Room 객체가 존재하고, 그 방 이름이 예상한 대로 일치하는지 검증합니다.
    @Test
    void testFindByRoomName() {
        // Act
        Optional<Room> foundRoom = roomRepository.findByRoomName(testRoom.getRoomName());

        // Assert
        assertThat(foundRoom).isPresent();
        assertThat(foundRoom.get().getRoomName()).isEqualTo(testRoom.getRoomName());
    }

    // 존재하지 않는 방 이름으로 findByRoomName 메서드를 호출합니다.
    // 조회된 Room 객체가 존재하지 않는지 검증합니다.
    @Test
    void testFindByRoomName_NotFound() {
        // Act
        Optional<Room> foundRoom = roomRepository.findByRoomName("non-existent-room-name");

        // Assert
        assertThat(foundRoom).isNotPresent();
    }
}