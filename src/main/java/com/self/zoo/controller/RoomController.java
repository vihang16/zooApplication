package com.self.zoo.controller;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.service.RoomService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/rooms")
public class RoomController {

    RoomService roomService;

    @PostMapping("/add")
    public RoomDto addRoom(@Valid @RequestBody RoomDto roomDto){
        return roomService.addRoom(roomDto);
    }

    @GetMapping("/{id}")
    public RoomDto retrieveRoom( @PathVariable Long id, @RequestParam(value = "parameter", defaultValue = "title") String parameter, @RequestParam(value = "by", defaultValue = "asc") String by){
         return roomService.getRoomDetails(id, parameter, by);
    }

    @PutMapping("/update/{id}")
   public RoomDto updateRoom(@RequestBody RoomDto room,@PathVariable Long id){
        room.setId(id);
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

    @GetMapping("/animals/{id}")
    public List<AnimalDto> getAllAnimalsInRoom(@PathVariable Long id, @RequestParam(value = "parameter", defaultValue = "title") String parameter, @RequestParam(value = "by", defaultValue = "asc") String by){
        return roomService.getAllAnimalsInRoom(id, parameter, by);
    }

}
