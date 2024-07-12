package kr.ac.itc.inhachatbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.itc.inhachatbot.dto.RoomDTO;
import kr.ac.itc.inhachatbot.exception.NotFoundException;
import kr.ac.itc.inhachatbot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Room 관련 REST 컨트롤러
 * <p>
 * 이 컨트롤러는 새로운 채팅방을 시작하거나 기존 채팅방을 조회하는 기능을 제공합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/room")
@Tag(name = "Room", description = "Room 관련 API")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 새로운 채팅을 시작하거나 기존 방을 조회
     * <p>
     * 주어진 방 UUID가 없으면 새로운 채팅방을 생성하고, 주어진 방 UUID가 있으면 해당 방을 조회합니다.
     * </p>
     * @param roomName 방 UUID (선택사항)
     * @return 생성되거나 조회된 RoomDTO를 포함하는 ResponseEntity
     */
    @Operation(summary = "새로운 채팅을 시작하거나 기존 방을 조회")
    @PostMapping("/start")
    public ResponseEntity<RoomDTO> startChat(@RequestParam(required = false) String roomName) {
        RoomDTO roomDTO = roomService.getOrCreateRoom(roomName);
        return ResponseEntity.ok(roomDTO);
    }

    /**
     * UUID로 방을 조회
     * <p>
     * 주어진 방 UUID를 통해 방을 조회하고, 해당 방이 없으면 NotFoundException을 던집니다.
     * </p>
     * @param uuid 방 UUID
     * @return 조회된 RoomDTO를 포함하는 ResponseEntity
     */
    @Operation(summary = "UUID로 방을 조회")
    @GetMapping("/{uuid}")
    public ResponseEntity<RoomDTO> getRoomByUuid(@PathVariable String uuid) {
        Optional<RoomDTO> roomDTO = roomService.getRoomByUuid(uuid);
        return roomDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("방을 찾을 수 없습니다: " + uuid));
    }
}