package kr.ac.itc.inhachatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient 설정 관련 클래스
 * <p>
 * 이 클래스는 WebClient를 설정하고 Bean으로 등록하기 위한 설정을 포함합니다.
 * WebClient는 비동기 및 리액티브 HTTP 요청을 처리하는 데 사용됩니다.
 * </p>
 */
@Configuration
public class WebClientConfig {

    @Value("${FASTAPI_URL}")
    private String FASTAPI_URL;
    /**
     * 설정된 WebClient 반환
     * <p>
     * WebClient를 Bean으로 등록하여 애플리케이션 전반에서 사용할 수 있도록 합니다.
     * </p>
     * @return WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(FASTAPI_URL)
                .build();
    }
}