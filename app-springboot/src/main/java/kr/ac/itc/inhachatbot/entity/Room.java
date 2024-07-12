package kr.ac.itc.inhachatbot.entity;

import jakarta.persistence.*;
import kr.ac.itc.inhachatbot.common.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * 채팅방 엔티티
 * <p>
 * 이 클래스는 채팅방을 나타내며, 방의 이름과 생성 시간 정보를 포함합니다.
 * </p>
 */
@Getter
@Entity
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNum;

    private String roomName;

    /**
     * 랜덤 UUID를 통해 채팅방을 초기화하는 생성자
     * <p>
     * 생성자 호출 시 채팅방 이름은 랜덤 UUID로 설정됩니다.
     * </p>
     */
    @Builder
    public Room() {
        this.roomName = UUID.randomUUID().toString();
    }
}