package kr.ac.itc.inhachatbot.dto;

import kr.ac.itc.inhachatbot.entity.Message;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Message 엔티티의 데이터 전송 객체 (DTO)
 * <p>
 * 이 클래스는 Message 엔티티의 데이터를 전송하기 위해 사용됩니다.
 * </p>
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDTO {

    private Boolean isUser;
    private String content;
    private LocalDateTime createdTime;

    @Builder
    public MessageDTO(Boolean isUser, String content, LocalDateTime createdTime) {
        this.isUser = isUser;
        this.content = content;
        this.createdTime = createdTime;
    }

    /**
     * Message 엔티티를 MessageDTO로 변환
     * <p>
     * 주어진 Message 엔티티를 기반으로 MessageDTO 객체를 생성합니다.
     * </p>
     * @param message Message 엔티티
     * @return MessageDTO 객체
     */
    public static MessageDTO of(Message message) {
        return MessageDTO.builder()
                .isUser(message.getIsUser())
                .content(message.getContent())
                .createdTime(message.getCreatedTime())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return Objects.equals(isUser, that.isUser) &&
                Objects.equals(content, that.content) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isUser, content, createdTime);
    }
}