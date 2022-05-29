package com.self.zoo.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "room")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generator = "seq_room_id"
    //@SequenceGenerator(name = "seq_room_id",allocationSize = 1, initialValue = 3)
    Long id;

    String title;

    @Column(name = "room_size")
    Long roomSize;

    @CreatedDate
    LocalDateTime createdOn;

    @OneToMany(mappedBy = "rooms")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Favorite> favorites;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Animal> animals;

}
