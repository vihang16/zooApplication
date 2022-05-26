package com.self.zoo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("favoriteRoom")
    Set<Favorite> favorites;
    @JsonManagedReference("animalRoom")
    Set<Animal> animals;

}
