package com.self.zoo.mapper;

import com.self.zoo.dto.AnimalDto;
import com.self.zoo.dto.FavoriteDto;
import com.self.zoo.dto.RoomDto;
import com.self.zoo.entity.Animal;
import com.self.zoo.entity.Favorite;
import com.self.zoo.entity.Room;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.*;

@Mapper(componentModel = "spring")
public interface SourceToDestinationMapper {

    //@Mapping(ignore = true, source = "room", target = "room")
    Animal animalDtoToAnimal(AnimalDto animal);
    AnimalDto animalToAnimalDto( Animal animal);

    List<AnimalDto> animalsToAnimalDtos(List<Animal> animals);

    Room roomDtoToRoom(RoomDto roomDto);

    RoomDto roomToRoomDto(Room room);

    Set<Favorite> favoriteDtosToFavorite(Set<FavoriteDto> favoriteRooms);

    @AfterMapping
    default Set<Favorite> afterMappingFavoriteDtosToFavorite(@MappingTarget Set<Favorite> favorite, Set<FavoriteDto> favoriteDtos){
        Set<Favorite> favorite1 = new HashSet<>();
        for(FavoriteDto favoriteDto: favoriteDtos){
            Favorite favoriteObj = new Favorite();
            favoriteObj.setId(favoriteDto.getId());
            favoriteObj.setRooms(roomDtoToRoom(favoriteDto.getRoomDto()));
            favorite1.add(favoriteObj);
        }
        return favorite1;
    }

    List<RoomDto> roomsToRoomDto(List<Room> rooms);


}
