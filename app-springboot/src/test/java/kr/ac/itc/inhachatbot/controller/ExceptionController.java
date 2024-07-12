package kr.ac.itc.inhachatbot.controller;

import kr.ac.itc.inhachatbot.dto.MessageDTO;
import kr.ac.itc.inhachatbot.exception.InvalidRequestException;
import kr.ac.itc.inhachatbot.exception.InvalidResponseException;
import kr.ac.itc.inhachatbot.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 예외를 발생시키는 테스트용 컨트롤러
 */
@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/some-not-found")
    public void throwNotFoundException() {
        throw new NotFoundException("Not Found");
    }

    @GetMapping("/invalid-request")
    public void throwInvalidRequestException() {
        throw new InvalidRequestException("Invalid Request");
    }

    @GetMapping("/invalid-response")
    public void throwInvalidResponseException() {
        MessageDTO messageDTO = MessageDTO.builder()
                .isUser(false)
                .content("챗봇과의 통신 중 오류가 발생했습니다. 올바른 질문으로 다시 시도해주세요.")
                .createdTime(LocalDateTime.now())
                .build();
        throw new InvalidResponseException("Invalid Response", messageDTO);
    }

    @GetMapping("/global-exception")
    public void throwGlobalException() {
        throw new RuntimeException("Internal Server Error");
    }
}