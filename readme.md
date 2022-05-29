# Sample Zoo project using Spring-boot
## Description of the project
this project is to demonstrate simple _Zoo_ functionalities like
1. Adding animal to zoo.
2. Add room to zoo.
3. Add animal to room.
4. Remove animal from room.
5. Find all animals in given room in sorted order.
6. Mark room as favorite for animal.
7. Unmarked room as favorite for animal.
8. Find the animals without having in sorted order.
9. Generate happiness report.

Below are technologies stack used:

| Name      | Version   |
|-----------|-----------|
| Spring    | 2.7.0     |
| Lombok    | 1.8.24    |
| Java      | 11        |
| mapstruct | 1.5.0.RC1 |
| maven     | 4.0.0     |
| H2        |           |

This project generates 2 rooms at the time of running the application.

| room name | preference value |
|-----------|------------------|
| Green     | 20               |
| Blue      | 50               |

 Below are the end points that are exposed with functionality:

| Endpoint                 | Method Supported | Function                                                                                                                                                        |
|--------------------------|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| animal/add               | POST             | adding animal to db, it checks if Room is valid and constraint. It does not return Room details even if it is saved into db due to @JsonIgnore on RoomDto class |
| animal/all?{sort} & {by} | GET              | Get all animal data,with sorting capacity, default sort parameter is title and asc type                                                                         |
| animal/{id}              | GET              | Get specific animal details. It does not return Room details                                                                                                    |
| animal/{id}              | PUT              | update animal details, like adding favorite room, changing room, removing room, change preference etc                                                           |
| animal/remove/{id}       | DELETE           | Delete specific animal from animal table as well as from favorite table                                                                                         |
| animal/noRoom            | GET              | find animals without having room                                                                                                                                |
| animal/favorite/{id}     | GET              | find favorite room for animals                                                                                                                                  |
| rooms/add                | POST             | adding new room to Rooms table                                                                                                                                  |
| rooms/{id}               | GET              | retrieve data about Room, it does not retrieve list of animals for that room                                                                                    |
| rooms/update/{id}        | PUT              | update room details, but it does not update or remove animals from room                                                                                         |
| rooms/animals/{id}       | GET              | retrieve list of animals from given room                                                                                                                        |
| rooms/delete/{id}        | DELETE           | remove room from Room and favorite table and set null value to corresponding animals                                                                            |
| misc/happinessReport     | GET              | Get happiness report                                                                                                                                            |



