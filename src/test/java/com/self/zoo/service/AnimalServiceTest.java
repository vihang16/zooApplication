package com.self.zoo.service;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.repository.AnimalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    AnimalRepository animalRepository;

    @Autowired
    @InjectMocks
    AnimalService animalService;
    AnimalDto animalDto;
    RoomDto roomDto;

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
    void add() {

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