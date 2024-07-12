package kr.ac.itc.inhachatbot.controller;

import kr.ac.itc.inhachatbot.dto.RoomDTO;
import kr.ac.itc.inhachatbot.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = RoomController.class)
public class RoomControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RoomService roomService;

    private RoomDTO roomDTO;

    @BeforeEach
    void setUp() {
        roomDTO = RoomDTO.builder().roomName("test-room").build();
    }

    // 새로운 채팅을 시작하거나 기존 방을 조회하되 roomName이 제공되지 않은 상태에 대한 테스트입니다.
    // RoomService의 getOrCreateRoom 메서드가 호출될 때, 모킹된 RoomDTO 객체를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 POST 요청을 보내고, 응답의 상태 코드가 200 OK인지, 응답 본문이 예상한 RoomDTO인지 확인합니다.
    @Test
    void startChat_success_noRoomName() {
        // Arrange
        when(roomService.getOrCreateRoom(any())).thenReturn(roomDTO);

        // Act & Assert
        webTestClient.post()
                .uri("/api/room/start")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RoomDTO.class)
                .value(response -> {
                    assert response != null;
                    assert "test-room".equals(response.getRoomName());
                });
    }

    // 새로운 채팅을 시작하거나 기존 방을 조회하되 roomName이 제공되는 상태에 대한 테스트입니다.
    // RoomService의 getOrCreateRoom 메서드가 호출될 때, 모킹된 RoomDTO 객체를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 POST 요청을 보내고, 응답의 상태 코드가 200 OK인지, 응답 본문이 예상한 RoomDTO인지 확인합니다.
    @Test
    void startChat_success_withRoomName() {
        // Arrange
        when(roomService.getOrCreateRoom(anyString())).thenReturn(roomDTO);

        // Act & Assert
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/room/start").queryParam("roomName", "test-uuid").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(RoomDTO.class)
                .value(response -> {
                    assert response != null;
                    assert "test-room".equals(response.getRoomName());
                });
    }

    // 주어진 UUID로 방을 조회하는 테스트입니다.
    // RoomService의 getRoomByUuid 메서드가 호출될 때, 모킹된 Optional<RoomDTO> 객체를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 GET 요청을 보내고, 응답의 상태 코드가 200 OK인지, 응답 본문이 예상한 RoomDTO인지 확인합니다.
    @Test
    void getRoomByUuid_success() {
        // Arrange
        when(roomService.getRoomByUuid(anyString())).thenReturn(Optional.of(roomDTO));

        // Act & Assert
        webTestClient.get()
                .uri("/api/room/{uuid}", "test-uuid")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RoomDTO.class)
                .value(response -> {
                    assert response != null;
                    assert "test-room".equals(response.getRoomName());
                });
    }

    // 주어진 UUID로 방을 조회할 때 방이 존재하지 않는 경우를 테스트합니다.
    // RoomService의 getRoomByUuid 메서드가 호출될 때, 빈 Optional 객체를 반환하도록 설정합니다.
    // WebTestClient를 사용하여 GET 요청을 보내고, 응답의 상태 코드가 404 Not Found인지 확인합니다.
    @Test
    void getRoomByUuid_notFound() {
        // Arrange
        when(roomService.getRoomByUuid(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        webTestClient.get()
                .uri("/api/room/{uuid}", "test-uuid")
                .exchange()
                .expectStatus().isNotFound();
    }
}