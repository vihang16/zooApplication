package com.self.zoo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    Set<FavoriteDto> favorites;
    @JsonManagedReference("animalRoom")
    Set<AnimalDto> animals;

}
