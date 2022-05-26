package com.self.zoo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    @JsonBackReference("favoriteRoom")
    @ToString.Exclude
    RoomDto roomDto;
    @JsonBackReference("animalFavoriteRoom")
    @ToString.Exclude
    AnimalDto animalDto;
    Long id;
}
