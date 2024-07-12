package kr.ac.itc.inhachatbot.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomTest {

    // Room 엔티티를 빌더를 사용하여 생성합니다.
    // 생성된 Room 객체의 roomName 필드가 랜덤 UUID로 설정되어 있는지 검증합니다.
    @Test
    void testRoomBuilder() {
        // Act
        Room room = Room.builder().build();

        // Assert
        assertThat(room.getRoomName()).isNotNull();
        assertThat(room.getRoomName()).hasSize(36); // UUID는 36자
    }

    // 기본 생성자를 사용하여 Room 엔티티를 생성합니다.
    // 생성된 Room 객체의 roomName 필드가 랜덤 UUID로 설정되어 있는지 검증합니다.
    @Test
    void testRoomDefaultConstructor() {
        // Act
        Room room = new Room();

        // Assert
        assertThat(room.getRoomName()).isNotNull();
        assertThat(room.getRoomName()).hasSize(36); // UUID는 36자
    }
}