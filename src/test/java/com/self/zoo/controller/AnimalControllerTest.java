package com.self.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.FavoriteDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.exception.custom.InvalidRoomDetailException;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

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
    void getAllAnimals() throws Exception {
        AnimalDto animalDto = createAnimalDtoObject();
        when(animalService.getAllAnimals(any(), any())).thenReturn(new ArrayList<>(Arrays.asList(animalDto)));
        mvc.perform(MockMvcRequestBuilders
                .get("/animal/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title",is("dog")));
    }

    @Test
    void getAnimalById() throws Exception {
        AnimalDto animalDto = createAnimalDtoObject();
        when(animalService.getAnimalById(anyLong())).thenReturn(animalDto);
        mvc.perform(MockMvcRequestBuilders
                   .get("/animal/1")
                   .accept(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$.title",is("dog")));
    }

    @Test
    void updateAnimal() throws Exception {
        AnimalDto animalDto = createAnimalDtoObject();
        AnimalDto updateAnimal = createAnimalDtoObject();
        updateAnimal.setTitle("someoneElse");
        when(animalService.updateAnimal(any())).thenReturn(updateAnimal);
        mvc.perform(MockMvcRequestBuilders
                .put("/animal/1")
                .content(asJsonString(animalDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("someoneElse")));
    }

    @Test
    void removeAnimal() {
    }

    @Test
    void findAnimalsWithoutRoom() {
    }
}