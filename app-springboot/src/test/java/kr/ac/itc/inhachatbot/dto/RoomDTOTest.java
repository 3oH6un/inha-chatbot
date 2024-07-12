package kr.ac.itc.inhachatbot.dto;

import kr.ac.itc.inhachatbot.entity.Room;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomDTOTest {

    // Room 엔티티를 생성하고, RoomDTO.of 메서드를 사용하여 RoomDTO 객체로 변환합니다.
    // 변환된 RoomDTO 객체의 필드가 원본 Room 엔티티와 동일한지 검증합니다.
    @Test
    void testOf() {
        // Arrange
        Room room = new Room();

        // Act
        RoomDTO roomDTO = RoomDTO.of(room);

        // Assert
        assertThat(roomDTO.getRoomName()).isEqualTo(room.getRoomName());
    }

    // RoomDTO의 빌더를 사용하여 객체를 생성합니다.
    // 생성된 RoomDTO 객체의 필드가 예상한 대로 설정되었는지 검증합니다.
    @Test
    void testBuilder() {
        // Act
        RoomDTO roomDTO = RoomDTO.builder()
                .roomName("test-room")
                .build();

        // Assert
        assertThat(roomDTO.getRoomName()).isEqualTo("test-room");
    }
}