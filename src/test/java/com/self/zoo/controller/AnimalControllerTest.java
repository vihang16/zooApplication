package com.self.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.FavoriteDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AnimalController.class)
class AnimalControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AnimalService animalService;


    @Test
    void addAnimal() throws Exception {
        AnimalDto animalDto = createAnimalDtoObject();
        AnimalDto response = new AnimalDto();
        response.setId(1L);
        when(animalService.add(any())).thenReturn(response);
        mvc.perform(MockMvcRequestBuilders
                   .post("/animal/add")
                   .content(asJsonString(animalDto))
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andDo(MockMvcResultHandlers.print())
                   .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void addAnimalWithEmptyTitle() throws Exception{
        AnimalDto animalDto = createAnimalDtoObject();
        animalDto.setTitle(null);
        AnimalDto response = new AnimalDto();
        response.setId(1L);
        when(animalService.add(any())).thenThrow(new ConstraintViolationException("title should be given", null));
        mvc.perform(MockMvcRequestBuilders
                .post("/animal/add")
                .content(asJsonString(animalDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("title should be given")));
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

    private String asJsonString(Object animalDto) {
        try {
            return objectMapper.writeValueAsString(animalDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
}