package kr.ac.itc.inhachatbot.dto;

import kr.ac.itc.inhachatbot.entity.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Room 엔티티의 데이터 전송 객체 (DTO)
 * <p>
 * 이 클래스는 Room 엔티티의 데이터를 전송하기 위해 사용됩니다.
 * </p>
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDTO {

    private String roomName;

    @Builder
    public RoomDTO(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Room 엔티티를 RoomDTO로 변환
     * <p>
     * 주어진 Room 엔티티를 기반으로 RoomDTO 객체를 생성합니다.
     * </p>
     * @param room Room 엔티티
     * @return RoomDTO 객체
     */
    public static RoomDTO of(Room room) {
        return RoomDTO.builder()
                .roomName(room.getRoomName())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDTO roomDTO = (RoomDTO) o;
        return Objects.equals(roomName, roomDTO.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName);
    }
}