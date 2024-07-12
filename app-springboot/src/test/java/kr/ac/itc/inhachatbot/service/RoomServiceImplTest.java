package kr.ac.itc.inhachatbot.service;

import kr.ac.itc.inhachatbot.dto.RoomDTO;
import kr.ac.itc.inhachatbot.entity.Room;
import kr.ac.itc.inhachatbot.exception.NotFoundException;
import kr.ac.itc.inhachatbot.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    // RoomRepository 인스턴스를 모킹합니다.
    @Mock
    private RoomRepository roomRepository;

    // RoomServiceImpl 인스턴스를 주입합니다.
    @InjectMocks
    private RoomServiceImpl roomService;

    // 테스트용 Room 객체를 설정합니다.
    private Room testRoom;

    // 각 테스트 메서드 실행 전에 테스트용 Room 객체를 설정합니다.
    @BeforeEach
    void setUp() {
        testRoom = Room.builder().build();
    }

    // 새로운 Room을 생성하는 기능을 테스트합니다.
    // roomRepository.save 메서드가 호출되었는지 검증합니다.
    @Test
    void createRoom() {
        // Arrange
        when(roomRepository.save(any(Room.class))).thenReturn(testRoom);

        // Act
        RoomDTO roomDTO = roomService.createRoom();

        // Assert
        assertThat(roomDTO.getRoomName()).isEqualTo(testRoom.getRoomName());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    // 주어진 UUID로 Room을 조회하는 기능을 테스트합니다.
    // Room이 존재하는 경우, roomRepository.findByRoomName 메서드가 호출되었는지 검증합니다.
    @Test
    void getRoomByUuid_success() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.of(testRoom));

        // Act
        Optional<RoomDTO> roomDTO = roomService.getRoomByUuid(testRoom.getRoomName());

        // Assert
        assertThat(roomDTO).isPresent();
        assertThat(roomDTO.get().getRoomName()).isEqualTo(testRoom.getRoomName());
    }

    // 주어진 UUID로 Room을 조회하는 기능을 테스트합니다.
    // Room이 존재하지 않는 경우, 반환된 Optional 객체가 비어있는지 검증합니다.
    @Test
    void getRoomByUuid_notFound() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.empty());

        // Act
        Optional<RoomDTO> roomDTO = roomService.getRoomByUuid("non-existent-room-name");

        // Assert
        assertThat(roomDTO).isNotPresent();
    }

    // 주어진 UUID로 Room을 조회하거나 새로운 Room을 생성하는 기능을 테스트합니다.
    // Room이 존재하는 경우, 기존 Room을 반환하는지 검증합니다.
    @Test
    void getOrCreateRoom_existingRoom() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.of(testRoom));

        // Act
        RoomDTO roomDTO = roomService.getOrCreateRoom(testRoom.getRoomName());

        // Assert
        assertThat(roomDTO.getRoomName()).isEqualTo(testRoom.getRoomName());
        verify(roomRepository, times(1)).findByRoomName(anyString());
    }

    // 주어진 UUID로 Room을 조회하거나 새로운 Room을 생성하는 기능을 테스트합니다.
    // Room이 존재하지 않는 경우, 새로운 Room을 생성하는지 검증합니다.
    @Test
    void getOrCreateRoom_newRoom() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.empty());
        when(roomRepository.save(any(Room.class))).thenReturn(testRoom);

        // Act
        RoomDTO roomDTO = roomService.getOrCreateRoom("non-existent-room-name");

        // Assert
        assertThat(roomDTO.getRoomName()).isEqualTo(testRoom.getRoomName());
        verify(roomRepository, times(1)).findByRoomName(anyString());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    // 주어진 UUID로 Room 엔티티를 조회하는 기능을 테스트합니다.
    // Room이 존재하는 경우, Room 엔티티를 반환하는지 검증합니다.
    @Test
    void getRoomEntityByUuid_success() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.of(testRoom));

        // Act
        Room room = roomService.getRoomEntityByUuid(testRoom.getRoomName());

        // Assert
        assertThat(room.getRoomName()).isEqualTo(testRoom.getRoomName());
    }

    // 주어진 UUID로 Room 엔티티를 조회하는 기능을 테스트합니다.
    // Room이 존재하지 않는 경우, NotFoundException을 던지는지 검증합니다.
    @Test
    void getRoomEntityByUuid_notFound() {
        // Arrange
        when(roomRepository.findByRoomName(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> roomService.getRoomEntityByUuid("non-existent-room-name"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("채팅방이 올바르지 않습니다");
    }
}