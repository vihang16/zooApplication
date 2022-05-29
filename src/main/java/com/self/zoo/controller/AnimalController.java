package com.self.zoo.controller;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.exception.custom.InvalidRoomDetailException;
import com.self.zoo.service.AnimalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/animal")
public class AnimalController {

    AnimalService animalService;

    @PostMapping("/add")
    public AnimalDto addAnimal(@Valid @RequestBody AnimalDto animalDto) throws InvalidRoomDetailException {
        return animalService.add(animalDto);
    }

    @GetMapping("/all")
    public List<AnimalDto> getAllAnimals(@RequestParam(value = "parameter",defaultValue = "title") String sortingParameter, @RequestParam(value = "by", defaultValue = "asc") String by){
        return  animalService.getAllAnimals(sortingParameter, by);
    }

    @GetMapping("/{id}")
    public AnimalDto getAnimalById(@PathVariable Long id){
        return animalService.getAnimalById(id);
    }

    @PutMapping("/{id}")
    public AnimalDto updateAnimal(@RequestBody AnimalDto animalDto, @PathVariable Long id) throws InvalidRoomDetailException {
        animalDto.setId(id);
        return animalService.updateAnimal(animalDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeAnimal(@PathVariable Long id){
        animalService.removeAnimal(id);
    }


    @GetMapping("/noRoom")
    public List<AnimalDto> findAnimalsWithoutRoom(@RequestParam(value = "parameter",defaultValue = "title") String sortingParameter, @RequestParam(value = "by", defaultValue = "asc") String by){
        return animalService.findAnimalsWithoutRoom(sortingParameter, by);
    }

    @GetMapping("/favorite/{id}")
    public List<RoomDto> findFavoriteRoomsForAnimal(@PathVariable Long id){
        return animalService.findFavoriteRoomForAnimal(id);
    }



}
