package kr.ac.itc.inhachatbot.service;

import jakarta.transaction.Transactional;
import kr.ac.itc.inhachatbot.dto.RoomDTO;
import kr.ac.itc.inhachatbot.entity.Room;
import kr.ac.itc.inhachatbot.repository.RoomRepository;
import kr.ac.itc.inhachatbot.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Room 관련 서비스 구현 클래스
 * <p>
 * 이 클래스는 Room 관련 서비스를 구현합니다.
 * </p>
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO createRoom() {
        Room room = new Room();
        Room savedRoom = roomRepository.save(room);
        return RoomDTO.of(savedRoom);
    }

    @Override
    public Optional<RoomDTO> getRoomByUuid(String uuid) {
        return roomRepository.findByRoomName(uuid)
                .map(RoomDTO::of);
    }

    @Override
    public RoomDTO getOrCreateRoom(String roomUuid) {
        if (roomUuid == null || roomUuid.isEmpty()) {
            return createRoom();
        } else {
            Optional<RoomDTO> existingRoomDTO = getRoomByUuid(roomUuid);
            return existingRoomDTO.orElseGet(this::createRoom);
        }
    }

    @Override
    public Room getRoomEntityByUuid(String uuid) {
        return roomRepository.findByRoomName(uuid)
                .orElseThrow(() -> new NotFoundException("채팅방이 올바르지 않습니다: " + uuid));
    }
}