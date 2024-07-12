package kr.ac.itc.inhachatbot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 설정 관련 클래스
 * <p>
 * 이 클래스는 Swagger UI를 통해 API 문서를 생성하고 제공하기 위한 설정을 포함합니다.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Swagger로 생성된 OpenAPI 문서를 설정
     * <p>
     * API 제목, 버전, 설명과 같은 정보를 설정합니다.
     * </p>
     * @return 설정된 OpenAPI 문서 정보
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Chat API")
                        .version("1.0")
                        .description("Inha Chatbot API 문서"));
    }
}