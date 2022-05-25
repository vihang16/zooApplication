package com.self.zoo.repository;

import com.self.zoo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    public Optional<Favorite> findByAnimalId(Long id);
}
