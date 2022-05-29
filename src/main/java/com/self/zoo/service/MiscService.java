package com.self.zoo.service;

import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Room;
import com.self.zoo.repository.AnimalRepository;
import com.self.zoo.repository.RoomRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Transactional(readOnly = true)
public class MiscService {

    RoomRepository roomRepository;
    AnimalRepository animalRepository;

    public Map<String, Long> getHappinessReport() {
        Map<String, Long> map = new HashMap<>();
        Map<String, BiPredicate<Long,Long>> preferenceComaprison = new HashMap<>();
        List<Room> rooms = roomRepository.findAll();
        List<Animal> animals = animalRepository.findAll();
        preferenceComaprison.put(">=",(a,b) -> a >= b);
        preferenceComaprison.put(">", (a,b) -> a > b);
        preferenceComaprison.put("<=", (a,b) -> a <= b);
        preferenceComaprison.put("<", (a,b) -> a < b );
        preferenceComaprison.put("=", Long::equals);
        for(Animal animal: animals){
            String type = animal.getType();
            Long animalPreference = animal.getPreference();
            for(Room room: rooms) {
                Long roomPref = room.getRoomSize();
                if (animalPreference != null) {
                    if (preferenceComaprison.get(type).test(roomPref, animalPreference))
                        map.put(room.getTitle(), map.getOrDefault(room.getTitle(), 0L) + 1L);
                }
            }

            }
        return map;
    }
}
