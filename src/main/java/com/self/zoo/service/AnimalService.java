package com.self.zoo.service;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Favorite;
import com.self.zoo.entity.Room;
import com.self.zoo.exception.custom.InvalidRoomDetailException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Transactional(readOnly = true)
public class AnimalService {

    AnimalRepository animalRepository;
    RoomRepository roomRepository;
    SourceToDestinationMapper mapper;

    FavoriteRepository favoriteRepository;

    @Transactional
    public AnimalDto add(AnimalDto animalDto) throws InvalidRoomDetailException {
        Animal animal = mapper.animalDtoToAnimal(animalDto);
        isValidAnimal(animal);
        animal = animalRepository.save(animal);
        log.debug("animal object saved:{}",animal);
        List<Favorite> favoriteRoom = addFavortieRoom(animalDto, animal);
        if(animalDto.getRoomDto() != null) {
            Room room = addAnimalToRoom(animal, animalDto);
            animal.setRoom(room);
            animal = animalRepository.save(animal);
        }

        //animal = animalRepository.save(animal);
        if(favoriteRoom != null)
           animal.setFavoriteRooms(new HashSet<>(favoriteRoom));
        return mapper.animalToAnimalDto(animal);
    }

    private Room addAnimalToRoom(Animal animal, AnimalDto animalDto) throws InvalidRoomDetailException {
        Room  room =  mapper.roomDtoToRoom(animalDto.getRoomDto());
        Optional<Room> optionalRoom = roomRepository.findById(room.getId());
        if(optionalRoom.isPresent()){
          room = optionalRoom.get();
          Set<Animal> animals=  room.getAnimals();
          animals.add(animal);
          //room.setAnimals(animals);
          roomRepository.save(room);
        }else{
            throw new InvalidRoomDetailException("room does not exist");
        }
        return room;
    }

    private List<Favorite>  addFavortieRoom(AnimalDto animalDto, Animal animal) throws InvalidRoomDetailException {
        Set<Favorite> favoriteRooms = mapper.favoriteDtosToFavorite(animalDto.getFavoriteRooms());
        if(favoriteRooms  != null ){
            for(Favorite favoriteRoom: favoriteRooms){
                   favoriteRoom.setAnimal(animal);
                   if(favoriteRoom.getRooms() != null) {
                       Optional<Room> roomExist = roomRepository.findById(favoriteRoom.getRooms().getId());
                       if (roomExist.isEmpty())
                           throw new InvalidRoomDetailException("this favorite room does exist:" + favoriteRoom.getRooms().getTitle());
                       favoriteRoom.setRooms(roomExist.get());
                   }
            }
            return favoriteRepository.saveAll(favoriteRooms);
        }
        return null;
    }

    private void isValidAnimal(Animal animal) throws InvalidRoomDetailException {
        if(animal.getRoom() == null)
            return;
        Optional<Room> room = roomRepository.findById(animal.getRoom().getId());
        if(room.isEmpty())
            throw new InvalidRoomDetailException("Room does not exist");
    }

    @Transactional
    public List<AnimalDto> getAllAnimals(String sortingParamter, String by) {
        List<Animal> animals = animalRepository.findAll(Sort.by(Sort.Direction.fromString(by),sortingParamter));
        log.info("total animals:{}",animals.size());
        return mapper.animalsToAnimalDtos(animals);
    }

    public AnimalDto getAnimalById(long id) {
        Animal animal = animalRepository.getReferenceById(id);
        return mapper.animalToAnimalDto(animal);
    }

    @Transactional
    public AnimalDto updateAnimal(AnimalDto animalDto) throws InvalidRoomDetailException {
        Animal animal = mapper.animalDtoToAnimal(animalDto);
        animal = animalRepository.save(animal);
        log.debug("animal object saved:{}",animal);
        List<Favorite> favorite = addFavortieRoom(animalDto, animal);
        if(animalDto.getRoomDto() != null) {
            Room room = addAnimalToRoom(animal, animalDto);
            animal.setRoom(room);
            animal = animalRepository.save(animal);
        }
        if(favorite != null)
          animal.setFavoriteRooms(new HashSet<>(favorite));
        return mapper.animalToAnimalDto(animal);
    }

    @Transactional
    public void removeAnimal(Long id) {
        List<Favorite> favoriteRoom = favoriteRepository.findByAnimalId(id);
        favoriteRepository.deleteAll(favoriteRoom);
        animalRepository.deleteById(id);
    }

    @Transactional
    public AnimalDto updateRoom(RoomDto roomDto, Long id) {
        Animal animal= animalRepository.getReferenceById(id);
        Room room = mapper.roomDtoToRoom(roomDto);
        Animal a = updateAnimalRoom(room, animal);
        return mapper.animalToAnimalDto(a);
    }

    private Animal updateAnimalRoom(Room room, Animal animal) {
        animal.setRoom(room);
        animal = animalRepository.save(animal);
        return animal;
    }

   /* @Transactional
    public AnimalDto removeAnimalFromRoom(java.lang.Long id) {
        Animal animal= animalRepository.getReferenceById(id);
        Animal a = updateAnimalRoom(null, animal);
        return mapper.animalToAnimalDto(a);
    }*/

    public List<AnimalDto> findAnimalsWithoutRoom(String parameter, String defaultType) {
        List<Animal> animals = animalRepository.findByRoom(null, Sort.by(Sort.Direction.fromString(defaultType),parameter));
        return mapper.animalsToAnimalDtos(animals);
    }

    public List<RoomDto> findFavoriteRoomForAnimal(Long id) {
        List<Favorite> favoriteRooms = favoriteRepository.findByAnimalId(id);
        List<Room> rooms = new ArrayList<>();
        for(Favorite favorite: favoriteRooms){
            rooms.add(roomRepository.findById(favorite.getRooms().getId()).get());
        }
        return mapper.roomsToRoomDto(rooms);
    }
}
