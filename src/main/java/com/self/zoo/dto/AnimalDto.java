package com.self.zoo.dto;

import com.fasterxml.jackson.annotation.*;
import com.self.zoo.entity.Favorite;
import com.self.zoo.entity.Room;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    Long id;
    String title;
    LocalDateTime located;
    String type;
    Long preference;
    @JsonManagedReference("animalFavoriteRoom")
    @ToString.Exclude
    Set<FavoriteDto> favoriteRooms;
    @JsonBackReference("animalRoom")
    RoomDto room;

}
