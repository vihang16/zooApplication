package com.self.zoo.service;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.exception.custom.InvalidRoomDetailException;
import com.self.zoo.mapper.SourceToDestinationMapper;
import com.self.zoo.repository.AnimalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    AnimalRepository animalRepository;

    @Autowired
    @InjectMocks
    AnimalService animalService;
    AnimalDto animalDto;
    RoomDto roomDto;

    @Mock
    SourceToDestinationMapper mapper;

    @BeforeEach
    public void setUp(){
        animalDto = new AnimalDto();
        roomDto = new RoomDto();
        animalDto.setTitle("lion");
        animalDto.setPreference(20L);
        roomDto.setTitle("Green");
        animalDto.setRoomDto(roomDto);
    }

    @AfterEach
    public void tearDown(){
        animalDto = null;
        roomDto = null;
    }

    @Test
    @Disabled
    void add() throws InvalidRoomDetailException {
       AnimalDto savedAnimal = new AnimalDto();
       savedAnimal.setId(1L);
       when(animalRepository.save(any())).thenReturn(savedAnimal);
       AnimalDto result = animalService.add(animalDto);
       assertEquals(result.getId(), 1L);

    }

    @Test
    void getAllAnimals() {
    }

    @Test
    void getAnimalById() {
    }

    @Test
    void updateAnimal() {
    }

    @Test
    void removeAnimal() {
    }

    @Test
    void updateRoom() {
    }

    @Test
    void removeAnimalFromRoom() {
    }

    @Test
    void findAnimalsWithoutRoom() {
    }

    @Test
    void findFavoriteRoomForAnimal() {
    }
}