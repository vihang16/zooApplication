package com.self.zoo.service;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.FavoriteDto;
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
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
/*
    @Transactional(readOnly = false)
    public AnimalDto add(AnimalDto animalDto) throws InvalidRoomDetailException {
        Animal animal = mapper.animalDtoToAnimal(animalDto);
        isValidAnimal(animal);
        animal = animalRepository.save(animal);
        log.debug("animal object saved:",animal);
        List<Favorite> favoriteRoom = addFavortieRoom(animalDto, animal);
        if(favoriteRoom != null)
           animal.setFavoriteRooms(new HashSet<>(favoriteRoom));
        return mapper.animalToAnimalDto(animal);
    }

    private List<Favorite>  addFavortieRoom(AnimalDto animalDto, Animal animal) throws InvalidRoomDetailException {
        Set<Favorite> favoriteRooms = mapper.favoriteDtosToFavorite(animalDto.getFavoriteRooms());
        if(favoriteRooms  != null ){
            for(Favorite favoriteRoom: favoriteRooms){
                   favoriteRoom.setAnimal(animal);
                   Optional<Room> roomExist = roomRepository.findById(favoriteRoom.getRooms().getId());
                   if(roomExist.isEmpty())
                       throw new InvalidRoomDetailException("this room does exist:"+favoriteRoom.getRooms().getTitle());
                   favoriteRoom.setRooms(roomExist.get());
            }
            List<Favorite> favorites = favoriteRepository.saveAll(favoriteRooms);
            return favorites;
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
    public List<AnimalDto> getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();
        log.debug("total animals:",animals.size());
        return mapper.animalsToAnimalDtos(animals);
    }

    public AnimalDto getAnimalById(long id) {
        Animal animal = animalRepository.getReferenceById(id);
        return mapper.animalToAnimalDto(animal);
    }

    @Transactional(readOnly = false)
    public AnimalDto updateAnimal(AnimalDto animalDto) throws InvalidRoomDetailException {
        Animal animal = mapper.animalDtoToAnimal(animalDto);
        animal = animalRepository.save(animal);
        log.debug("animal object saved:",animal);
        List<Favorite> favorite = addFavortieRoom(animalDto, animal);
        if(favorite != null)
          animal.setFavoriteRooms(new HashSet<>(favorite));
        return mapper.animalToAnimalDto(animal);
    }

    @Transactional(readOnly = false)
    public void removeAnimal(Long id) {
        Optional<Favorite> favoriteRoom = favoriteRepository.findByAnimalId(id);
        if(favoriteRoom.isPresent()) favoriteRepository.delete(favoriteRoom.get());
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
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

    @Transactional(readOnly = false)
    public AnimalDto removeAnimalFromRoom(java.lang.Long id) {
        Animal animal= animalRepository.getReferenceById(id);
        Animal a = updateAnimalRoom(null, animal);
        return mapper.animalToAnimalDto(a);
    }

    public List<AnimalDto> findAnimalsWithoutRoom(String parameter, String defaultType) {
        List<Animal> animals = animalRepository.findByRoom(null, Sort.by(Sort.Direction.fromString(defaultType),parameter));
        return mapper.animalsToAnimalDtos(animals);
    }*/

    @Transactional(readOnly = false)
    public Animal add(Animal animal) throws InvalidRoomDetailException {

        isValidAnimal(animal);
        Animal savedAnimal = animalRepository.save(animal);
        log.debug("animal object saved:",animal);
        List<Favorite> favoriteRoom = addFavortieRoom(animal, savedAnimal);
        if(favoriteRoom != null)
            animal.setFavoriteRooms(new HashSet<>(favoriteRoom));
        return animal;
    }

    private List<Favorite>  addFavortieRoom(Animal incomingAnimal, Animal savedAnimal) throws InvalidRoomDetailException {
        Set<Favorite> favoriteRooms = incomingAnimal.getFavoriteRooms();
        if(favoriteRooms  != null ){
            for(Favorite favoriteRoom: favoriteRooms){
                if(favoriteRoom != null && favoriteRoom.getRooms() != null) {
                    favoriteRoom.setAnimal(savedAnimal);
                    Optional<Room> roomExist = roomRepository.findById(favoriteRoom.getRooms().getId());
                    if (roomExist.isEmpty())
                        throw new InvalidRoomDetailException("this room does exist:" + favoriteRoom.getRooms().getTitle());
                    favoriteRoom.setRooms(roomExist.get());
                }
            }
            List<Favorite> favorites = favoriteRepository.saveAll(favoriteRooms);
            return favorites;
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
    public List<Animal> getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();
        log.debug("total animals:",animals.size());
        return animals;
    }

    public Animal getAnimalById(long id) {
        Animal animal = animalRepository.getReferenceById(id);
        return animal;
    }

    @Transactional(readOnly = false)
    public Animal updateAnimal(Animal animal) throws InvalidRoomDetailException {
        //Animal animal = mapper.animalDtoToAnimal(animalDto);
        Animal savedAnimal = animalRepository.save(animal);
        log.debug("animal object saved:",animal);
        List<Favorite> favorite = addFavortieRoom(animal, savedAnimal);
        if(favorite != null)
            animal.setFavoriteRooms(new HashSet<>(favorite));
        return animal;
    }

    @Transactional(readOnly = false)
    public void removeAnimal(Long id) {
        Optional<Favorite> favoriteRoom = favoriteRepository.findByAnimalId(id);
        if(favoriteRoom.isPresent()) favoriteRepository.delete(favoriteRoom.get());
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Animal updateRoom(RoomDto roomDto, Long id) {
        Animal animal= animalRepository.getReferenceById(id);
        Room room = mapper.roomDtoToRoom(roomDto);
        Animal a = updateAnimalRoom(room, animal);
        return a;
    }

    private Animal updateAnimalRoom(Room room, Animal animal) {
        animal.setRoom(room);
        animal = animalRepository.save(animal);
        return animal;
    }

    @Transactional(readOnly = false)
    public Animal removeAnimalFromRoom(java.lang.Long id) {
        Animal animal= animalRepository.getReferenceById(id);
        Animal a = updateAnimalRoom(null, animal);
        return a;
    }

    public List<Animal> findAnimalsWithoutRoom(String parameter, String defaultType) {
        List<Animal> animals = animalRepository.findByRoom(null, Sort.by(Sort.Direction.fromString(defaultType),parameter));
        return animals;
    }
}
