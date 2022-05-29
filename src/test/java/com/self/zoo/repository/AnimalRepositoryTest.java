package com.self.zoo.repository;

import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AnimalRepositoryTest {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    RoomRepository roomRepository;
    Animal animal;
    Room room;

    @BeforeEach
    public void setUp(){
        animal = new Animal();
        room = new Room();
        animal.setType(">");
        animal.setPreference(20L);
        animal.setTitle("Lion");
        room.setTitle("Green");
        animal.setRoom(room);
    }

    @Test
    void findByRoom() {
        roomRepository.save(room);
        animalRepository.save(animal);
        List<Animal> animalList = animalRepository.findByRoom(room);
        assertEquals(animalList.size(), 1);
    }

    @Test
    void simpleAnimalAdd(){
        animalRepository.save(animal);
        Animal fetchedAnimal =  animalRepository.findById(animal.getId()).get();
        assertEquals(1, fetchedAnimal.getId());
    }

    @Test
    void simpleAnimalUpdate(){
        animalRepository.save(animal);
        assertEquals("Lion", animal.getTitle());
        animal.setTitle("Zebra");
        animalRepository.save(animal);
        Animal fetchedAnimal =  animalRepository.findById(animal.getId()).get();
        assertEquals("Zebra", fetchedAnimal.getTitle());
    }

    @Test
    void testFindByRoom() {roomRepository.save(room);
       Animal animal1 = new Animal();
       animal1.setTitle("Dog");
       animal1.setPreference(20L);
       animal1.setRoom(room);
       animal.setRoom(room);
       animalRepository.save(animal1);
       animalRepository.save(animal);
       List<Animal> animals = animalRepository.findByRoom(room, Sort.by(Sort.Direction.fromString("asc"), "title"));
       assertEquals(animals.get(0).getTitle(), "Dog");
    }

    @Test
    void simpleAnimalDelete(){
        animalRepository.save(animal);
        animalRepository.deleteById(animal.getId());
        Optional<Animal> optionalAnimal =  animalRepository.findById(animal.getId());
        assertEquals(Optional.empty(),optionalAnimal);
    }
}