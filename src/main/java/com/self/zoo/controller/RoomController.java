package com.self.zoo.controller;

import com.self.zoo.dto.RoomDto;
import com.self.zoo.service.RoomService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public RoomDto retrieveRoom(@RequestParam Long id){
         return roomService.getRoomDetails(id);
    }

   /* public RoomDto updateRoom(@RequestBody RoomDto room,@RequestParam Long id){
        room.setId(id);
        return roomService.updateRoom(room);
    }*/

}
