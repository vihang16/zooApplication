package com.self.zoo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.self.zoo.ZooApplication;
import com.self.zoo.ZooConfiguration;
import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.FavoriteDto;
import com.self.zoo.dto.RoomDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = ZooApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class AnimalControllerIntTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    String localHost = "http://localhost:";

    @Test
    public void tesAddAnimal(){
        AnimalDto animalDto = createAnimalDtoObject();
        ResponseEntity<AnimalDto> response = this.restTemplate.postForEntity(this.localHost+port+"/animal/add", animalDto, AnimalDto.class);
        assertEquals(1L, response.getBody().getId());
    }

    private AnimalDto createAnimalDtoObject() {
        AnimalDto animalDto = new AnimalDto();
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomSize(25L);
        roomDto.setTitle("Green");
        RoomDto roomDto1 = new RoomDto();
        roomDto1.setRoomSize(10L);
        roomDto1.setTitle("Blue");
        animalDto.setRoomDto(roomDto);
        //animalDto.setFavoriteRooms();
        FavoriteDto f1 = new FavoriteDto();
        f1.setAnimalDto(animalDto);
        f1.setRoomDto(roomDto);
        FavoriteDto f2 = new FavoriteDto();
        f2.setAnimalDto(animalDto);
        f2.setRoomDto(roomDto1);
        Set<FavoriteDto> favSet = new HashSet<>();
        favSet.add(f2);
        favSet.add(f1);
        animalDto.setTitle("dog");
        animalDto.setFavoriteRooms(favSet);
        return animalDto;
    }

}
