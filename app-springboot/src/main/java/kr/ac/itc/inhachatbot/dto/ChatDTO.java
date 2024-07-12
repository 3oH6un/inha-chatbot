package kr.ac.itc.inhachatbot.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Fastapi 서버와의 통신을 위한 데이터 전송 객체 (DTO)
 * <p>
 * 이 클래스는 Fastapi 서버와의 통신을 위해 사용됩니다.
 * </p>
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatDTO {

    private String content;

    @Builder
    public ChatDTO(String content) {
        this.content = content;
    }
}
