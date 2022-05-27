package com.self.zoo.repository;

import com.self.zoo.entity.Favorite;
import com.self.zoo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

     List<Favorite> findByAnimalId(Long id);
     List<Favorite> findByRooms(Room room);
}
