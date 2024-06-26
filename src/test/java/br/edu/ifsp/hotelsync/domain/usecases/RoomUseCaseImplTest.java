package br.edu.ifsp.hotelsync.domain.usecases;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.room.find.FindOneRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.find.FindOneRoomUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.room.update.UpdateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.update.UpdateRoomUseCaseImpl;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RoomUseCaseImplTest {

    @Mock
    RoomDao repository;

    Faker faker = new Faker();

    @Nested
    @DisplayName("Create Room Use Case")
    class CreateRoomTest {

        private CreateRoomUseCase sut;

        @BeforeEach
        void setUp(){
            sut = new CreateRoomUseCaseImpl(repository);
        }

        @Test
        @DisplayName("when successfully creating a room")
        public void createValid(){
            CreateRoomUseCase.RequestModel request = new CreateRoomUseCase.RequestModel(
                    10,
                    2,
                    "King",
                    RoomCategory.STANDARD,
                    RoomStatus.AVAILABLE,
                    "ROOM!!!",
                    10L
            );

            sut.createRoom(request);
            verify(repository).save(any());
        }

        @Test
        @DisplayName("when failing to create a room")
        public void createInvalid(){
            CreateRoomUseCase.RequestModel request = new CreateRoomUseCase.RequestModel(
                    -20,
                    10,
                    "",
                    RoomCategory.STANDARD,
                    RoomStatus.AVAILABLE,
                    null,
                    -20L
            );

            assertThrows(IllegalArgumentException.class, () -> sut.createRoom(request));
        }

    }

    @Nested
    @DisplayName("Find Room Use Cases")
    public class FindRoomTest{
        private FindOneRoomUseCase sut;

        @BeforeEach
        public void setUp(){
            sut = new FindOneRoomUseCaseImpl(repository);
        }

        @Test
        @DisplayName("should find a room")
        public void findValid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(createRoom(id));

            assertDoesNotThrow(() -> sut.findRoom(id));
        }

        @Test
        @DisplayName("should not find nonexistent room")
        public void findInvalid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> sut.findRoom(id));
        }
    }

    @Nested
    @DisplayName("Update Use Case")
    class UpdateUseCase{
        public UpdateRoomUseCase sut;

        @BeforeEach
        public void setUp(){
            sut = new UpdateRoomUseCaseImpl(repository);
        }

        @Test
        @DisplayName("should update existing room")
        public void updateValid(){
            long id = 10L;
            when(repository.existsByKey(id)).thenReturn(true);

            UpdateRoomUseCase.RequestModel request = new UpdateRoomUseCase.RequestModel(
                    id,
                    20,
                    1,
                    "King",
                    RoomCategory.STANDARD,
                    "Real",
                    RoomStatus.AVAILABLE,
                    20L);

            sut.updateRoom(request);
            verify(repository).update(any());
        }

        @Test
        @DisplayName("should not update room that doesn't exist")
        public void updateNotFound(){
            long id = 10L;
            when(repository.existsByKey(id)).thenReturn(false);

            UpdateRoomUseCase.RequestModel request = new UpdateRoomUseCase.RequestModel(
                    id,
                    20,
                    1,
                    "King",
                    RoomCategory.STANDARD,
                    "Real",
                    RoomStatus.AVAILABLE,
                    20L);

            assertThrows(NoSuchElementException.class, () -> sut.updateRoom(request));
        }

    }


    private Optional<Room> createRoom(Long id){
        return Optional.of(
                Room.createRoomWithId(
                    id,
                    faker.number().numberBetween(10,20),
                    faker.number().numberBetween(2, 5),
                    "King",
                    RoomCategory.STANDARD,
                    faker.pokemon().name(),
                    RoomStatus.AVAILABLE,
                    faker.number().randomDouble(2, 5L, 10L)
            ));
    }

}