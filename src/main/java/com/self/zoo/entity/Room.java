package com.self.zoo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "room")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_room_id")
    @SequenceGenerator(name = "seq_room_id")
    //@JsonView(Views.Public.class)
    Long id;

    //@JsonView(Views.Public.class)
    String title;

    @Column(name = "room_size")
    //@JsonView(Views.Public.class)
    Long roomSize;

    @CreatedDate
    LocalDateTime createdOn;

    @OneToMany(mappedBy = "rooms")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@JsonManagedReference(value = "favoriteRooms")
    Set<Favorite> favorites;

    @OneToMany(mappedBy = "room")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@JsonManagedReference(value = "animals")
    Set<Animal> animals;

}
