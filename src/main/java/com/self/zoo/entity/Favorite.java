package com.self.zoo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "favorite_rooms")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fav_id")
    @SequenceGenerator(name = "seq_fav_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    //@JsonBackReference(value = "favoriteAnimals")
    Animal animal;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    //@JsonBackReference(value = "favoriteRooms")
    Room rooms;
}
