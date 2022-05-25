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
/*
    AnimalService animalService;

    @PostMapping("/add")
    public AnimalDto addAnimal(@Valid @RequestBody AnimalDto animalDto) throws InvalidRoomDetailException {
        return animalService.add(animalDto);
    }

    @GetMapping
    public List<AnimalDto> getAllAnimals(){
        return  animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDto getAnimalById(@PathVariable long id){
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

    @PatchMapping("/room/{id}")
    public AnimalDto updateRoom(@RequestBody RoomDto room, @PathVariable Long id){
        return animalService.updateRoom(room, id);
    }

    @PatchMapping("/roomRemove/{id}")
    public AnimalDto removeAnimalFromRoom(@PathVariable Long id){
        return animalService.removeAnimalFromRoom(id);
    }

    @GetMapping("/noRoom/")
    public List<AnimalDto> findAnimalsWithoutRoom(@RequestParam("parameter") Optional<String> sortingParamter, @RequestParam("by") Optional<String> by){
        String parameter = sortingParamter.orElse("title");
        String defaultType = by.orElse("asc");
        return animalService.findAnimalsWithoutRoom(parameter, defaultType);
    }*/

    AnimalService animalService;

    @PostMapping("/add")
    public Animal addAnimal(@Valid @RequestBody Animal animalDto) throws InvalidRoomDetailException {
        return animalService.add(animalDto);
    }

    @GetMapping
    public List<Animal> getAllAnimals(){
        return  animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable long id){
        return animalService.getAnimalById(id);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@RequestBody Animal animalDto, @PathVariable Long id) throws InvalidRoomDetailException {
        animalDto.setId(id);
        return animalService.updateAnimal(animalDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeAnimal(@PathVariable Long id){
        animalService.removeAnimal(id);
    }

    @PatchMapping("/room/{id}")
    public Animal updateRoom(@RequestBody RoomDto room, @PathVariable Long id){
        return animalService.updateRoom(room, id);
    }

    @PatchMapping("/roomRemove/{id}")
    public Animal removeAnimalFromRoom(@PathVariable Long id){
        return animalService.removeAnimalFromRoom(id);
    }

    @GetMapping("/noRoom/")
    public List<Animal> findAnimalsWithoutRoom(@RequestParam("parameter") Optional<String> sortingParamter, @RequestParam("by") Optional<String> by){
        String parameter = sortingParamter.orElse("title");
        String defaultType = by.orElse("asc");
        return animalService.findAnimalsWithoutRoom(parameter, defaultType);
    }

}
