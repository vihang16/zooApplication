package com.self.zoo.repository;

import java.util.List;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

     List<Animal> findByRoom(Room room, Sort sort);
     List<Animal> findByRoom(Room room);
}
