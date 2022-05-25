package com.self.zoo.service;

import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Room;
import com.self.zoo.mapper.SourceToDestinationMapper;
import com.self.zoo.repository.RoomRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@NoArgsConstructor
public class RoomService {

    RoomRepository roomRepository;
    SourceToDestinationMapper mapper;
    public RoomDto addRoom(RoomDto roomDto) {
        Room room = mapper.roomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        log.debug("room saved:",room);
        return mapper.roomToRoomDto(room);
    }

    public RoomDto getRoomDetails( Long id) {
        Room room = roomRepository.getReferenceById(id);
        return mapper.roomToRoomDto(room);
    }

    //public RoomDto updateRoom(RoomDto room) {
    //}
}
