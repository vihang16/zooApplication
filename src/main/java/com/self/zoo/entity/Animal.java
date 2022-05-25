package com.self.zoo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_animal_id")
    @SequenceGenerator(name = "seq_animal_id")
    //@JsonView(Views.Public.class)
    Long id;

    @NotEmpty(message = "title should be given")
    //@JsonView(Views.Public.class)
    String title;

    @CreatedDate
    //@JsonView(Views.Public.class)
    LocalDateTime located;

    //@JsonView(Views.Public.class)
    String type;
    //@JsonView(Views.Public.class)
    Long preference;

    @OneToMany(mappedBy = "animal")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@JsonManagedReference(value = "favoriteAnimals")
    Set <Favorite> favoriteRooms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    //@JsonBackReference(value = "animals")
    Room room;
}
