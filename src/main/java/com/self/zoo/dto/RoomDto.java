package com.self.zoo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Favorite;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {

    Long id;
    String title;
    Long roomSize;
    LocalDateTime createdOn;
    Set<Favorite> favorites;
    Set<Animal> animals;

}
