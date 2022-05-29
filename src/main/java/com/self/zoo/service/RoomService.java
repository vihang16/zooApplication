package com.self.zoo.service;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Favorite;
import com.self.zoo.entity.Room;
import com.self.zoo.mapper.SourceToDestinationMapper;
import com.self.zoo.repository.AnimalRepository;
import com.self.zoo.repository.FavoriteRepository;
import com.self.zoo.repository.RoomRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class RoomService {

    RoomRepository roomRepository;
    AnimalRepository animalRepository;

    FavoriteRepository favoriteRepository;
    SourceToDestinationMapper mapper;
    public RoomDto addRoom(RoomDto roomDto) {
        Room room = mapper.roomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        log.debug("room saved:{}",room);
        return mapper.roomToRoomDto(room);
    }

    public RoomDto getRoomDetails(Long id, String parameter, String by) {
        Room room = roomRepository.findById(id).get();
        List<Animal> animals = animalRepository.findByRoom(room, Sort.by(Sort.Direction.fromString(by),parameter));
        room.setAnimals(new HashSet<>(animals));
        return mapper.roomToRoomDto(room);
    }

    public RoomDto updateRoom(RoomDto roomDto) {
        Room room = mapper.roomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        return mapper.roomToRoomDto(room);
    }

    public void deleteRoom(Long id) {
        Optional<Room> optionalRoom =  roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            updateAnimalTable(room);
            updateFavorites(room);
            roomRepository.delete(room);

        }
    }

    private void updateFavorites(Room room) {
        List<Favorite> favorites =  favoriteRepository.findByRooms(room);
        favoriteRepository.deleteAll(favorites);
        log.info("rooms removed from favorites");
    }

    private void updateAnimalTable(Room room) {
        List<Animal> animals = animalRepository.findByRoom(room);
        for(Animal animal: animals){
            animal.setRoom(null);
            animalRepository.save(animal);
        }
        log.info("animal updated successfully");
    }

    public List<AnimalDto> getAllAnimalsInRoom(Long id, String parameter, String by) {
        Room room = roomRepository.findById(id).get();
        List<Animal> animals = animalRepository.findByRoom(room, Sort.by(Sort.Direction.fromString(by),parameter));
        return mapper.animalsToAnimalDtos(animals);
    }
}
