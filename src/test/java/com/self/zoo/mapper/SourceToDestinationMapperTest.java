package com.self.zoo.mapper;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Room;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SourceToDestinationMapperTest {

    SourceToDestinationMapper mapper = Mappers.getMapper(SourceToDestinationMapper.class);

    @Test
    void animalDtoToAnimal() {
        AnimalDto animalDto =  new AnimalDto();
    }

    @Test
    void animalToAnimalDto() {
        Animal a = new Animal();
        a.setId(1L);
        a.setPreference(25l);
        a.setTitle("Dog");
        a.setType("<=");
        Room r = new Room();
        r.setTitle("pink");
        a.setRoom(r);
        AnimalDto animalDto =  mapper.animalToAnimalDto(a);
        assertEquals(animalDto.getRoomDto().getTitle(), a.getRoom().getTitle());
        assertEquals(animalDto.getTitle(), a.getTitle());
        assertEquals(animalDto.getId(), a.getId());
    }

    @Test
    void animalsToAnimalDtos() {
    }

    @Test
    void roomDtoToRoom() {
    }

    @Test
    void roomToRoomDto() {
    }
}