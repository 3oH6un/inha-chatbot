package kr.ac.itc.inhachatbot.entity;

import kr.ac.itc.inhachatbot.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 메시지 엔티티
 * <p>
 * 이 클래스는 채팅 메시지를 나타내며, 메시지의 내용, 발신자가 사용자인지 여부,
 * 그리고 메시지가 속한 채팅방 정보를 포함합니다.
 * </p>
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_num")
    private Room room;

    private Boolean isUser;

    @Column(nullable = false, length = 12000)
    private String content;

    /**
     * 채팅방, 사용자인지 여부, 채팅 내용을 통해 채팅메시지를 초기화하는 생성자
     * @param room 채팅방 엔티티
     * @param isUser 사용자인지 AI인지 여부
     * @param content 채팅 내용
     */
    @Builder
    public Message(Room room, Boolean isUser, String content) {
        this.room = room;
        this.isUser = isUser;
        this.content = content;
    }
}