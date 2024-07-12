package kr.ac.itc.inhachatbot.exception;

import kr.ac.itc.inhachatbot.controller.ExceptionController;
import kr.ac.itc.inhachatbot.dto.MessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExceptionController.class)
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    // MockMvc 인스턴스를 생성하여 REST API를 테스트합니다.
    @Autowired
    private MockMvc mockMvc;

    // 실제 예외를 발생시키는 컨트롤러를 모킹합니다.
    @MockBean
    private ExceptionController exceptionController; // 컨트롤러를 모킹하여 사용

    // 각 테스트 메서드 실행 전에 MockMvc를 설정합니다.
    @BeforeEach
    public void setup() {
        MessageDTO messageDTO = MessageDTO.builder()
                .isUser(false)
                .content("챗봇과의 통신 중 오류가 발생했습니다. 올바른 질문으로 다시 시도해주세요.")
                .createdTime(LocalDateTime.now())
                .build();
        doThrow(new NotFoundException("Not Found")).when(exceptionController).throwNotFoundException();
        doThrow(new InvalidRequestException("Invalid Request")).when(exceptionController).throwInvalidRequestException();
        doThrow(new InvalidResponseException("Invalid Response", messageDTO)).when(exceptionController).throwInvalidResponseException();
        doThrow(new RuntimeException("Internal Server Error")).when(exceptionController).throwGlobalException();
    }

    // NotFoundException을 처리하는 테스트입니다.
    // /api/some-not-found 엔드포인트를 호출하여 NotFoundException이 발생하고, 상태 코드가 404 Not Found인지 검증합니다.
    @Test
    void handleNotFoundException() throws Exception {
        mockMvc.perform(get("/api/some-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    // InvalidRequestException을 처리하는 테스트입니다.
    // /api/invalid-request 엔드포인트를 호출하여 InvalidRequestException이 발생하고, 상태 코드가 400 Bad Request인지 검증합니다.
    @Test
    void handleInvalidRequestException() throws Exception {
        mockMvc.perform(get("/api/invalid-request"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    // InvalidResponseException을 처리하는 테스트입니다.
    // /api/invalid-response 엔드포인트를 호출하여 InvalidResponseException이 발생하고, 상태 코드가 500 Internal Server Error인지 검증합니다.
    @Test
    void handleInvalidResponseException() throws Exception {
        mockMvc.perform(get("/api/invalid-response"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.isUser").value(false))
                .andExpect(jsonPath("$.content").value("챗봇과의 통신 중 오류가 발생했습니다. 올바른 질문으로 다시 시도해주세요."))
                .andExpect(jsonPath("$.createdTime").exists());
    }

    // 기타 모든 예외를 처리하는 테스트입니다.
    // /api/global-exception 엔드포인트를 호출하여 예외가 발생하고, 상태 코드가 500 Internal Server Error인지 검증합니다.
    @Test
    void handleGlobalException() throws Exception {
        mockMvc.perform(get("/api/global-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Internal Server Error"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}
