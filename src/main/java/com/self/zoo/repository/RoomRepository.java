package com.self.zoo.repository;

import com.self.zoo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>
{
}
