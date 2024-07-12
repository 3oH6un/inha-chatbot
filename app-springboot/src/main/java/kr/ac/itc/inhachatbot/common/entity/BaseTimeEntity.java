package kr.ac.itc.inhachatbot.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 엔티티의 생성 시간을 기록하기 위한 클래스
 * <p>
 * 이 클래스는 엔티티가 생성된 시간을 자동으로 기록하기 위해 사용됩니다.
 * JPA의 Auditing 기능을 활용하여, 생성 시간(createdTime)을 자동으로 설정합니다.
 * </p>
 */
@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseTimeEntity {

    /**
     * 엔티티가 생성된 시간.
     * 이 필드는 JPA Auditing에 의해 자동으로 설정됩니다.
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;
}
