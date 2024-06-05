package br.edu.ifsp.hotelsync.domain.usecases;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindOneReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindOneReservationUseCaseImpl;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReservationUseCaseImplTest {

    @Mock
    ReservationDao repository;
    @Mock
    Guest owner;
    @Mock
    Room room;

    Faker faker = new Faker();
    @Nested
    @DisplayName("Create Reservation Use Case")
    class CreateReservationTest {
        private LocalDate beginOfReservation;
        private LocalDate endDate;
        private CreateReservationUseCase sut;

        @BeforeEach
        void setUp(){
            sut = new CreateReservationUseCaseImpl(repository);
            beginOfReservation = LocalDate.now();

            endDate = beginOfReservation.plusDays(faker.number().numberBetween(1, 5));
        }

        @Nested
        @DisplayName("invalid reservation tests")
        class ValidCreations{

            @Test
            @DisplayName("Reservation is valid")
            public void createValidReservation(){

                CreateReservationUseCase.RequestModel request = new CreateReservationUseCase.RequestModel(
                        beginOfReservation,
                        endDate,
                        owner,
                        room
                );

                sut.createReservation(request);
                verify(repository).save(any());
            }
        }

        @Nested
        @DisplayName("invalid reservation tests")
        class InvalidCreations{

            @Test
            @DisplayName("create reservation without start date")
            public void createReservationWithoutStartDate(){
                CreateReservationUseCase.RequestModel request = new CreateReservationUseCase.RequestModel(
                        null,
                        beginOfReservation.plusDays(5),
                        owner,
                        room
                );

                assertThrows(IllegalArgumentException.class, () -> sut.createReservation(request));
            }
            @Test
            @DisplayName("create reservation without end date")
            public void createReservationWithoutEndDate(){
                CreateReservationUseCase.RequestModel request = new CreateReservationUseCase.RequestModel(
                        beginOfReservation,
                       null,
                        owner,
                        room
                );

                assertThrows(IllegalArgumentException.class, () -> sut.createReservation(request));
            }
        }
            @Test
            @DisplayName("create reservation without owner")
            public void createReservationWithoutOwner() {
                CreateReservationUseCase.RequestModel request = new CreateReservationUseCase.RequestModel(
                        beginOfReservation,
                        beginOfReservation.plusDays(5),
                        null,
                        room

                );

                assertThrows(IllegalArgumentException.class, () -> sut.createReservation(request));
            }

            @Test
            @DisplayName("create reservation without room")
            public void createReservationWithoutRoom() {
                CreateReservationUseCase.RequestModel request = new CreateReservationUseCase.RequestModel(
                        beginOfReservation,
                        beginOfReservation.plusDays(5),
                        owner,
                        null
                );

                assertThrows(IllegalArgumentException.class, () -> sut.createReservation(request));
            }
    }

    @Nested
    @DisplayName("Find Reservation Use Cases")
    public class FindReservationTest{
        private FindOneReservationUseCase sut;

        @BeforeEach
        public void setUp(){
            sut = new FindOneReservationUseCaseImpl(repository);
        }

        @Test
        public void findValid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(createReservation(id));

            assertDoesNotThrow(() -> sut.findOneById(new FindOneReservationUseCase.RequestModel(id)));
        }

        @Test
        public void findInvalid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> sut.findOneById(new FindOneReservationUseCase.RequestModel(id)));
        }
    }

    private Optional<Reservation> createReservation(Long id){
        return Optional.of(
                Reservation.createCompleteReservation(
                        id,
                        LocalDate.now(),
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        LocalDate.now().plusDays(5),
                        owner,
                        room,
                        null,
                        null,
                        null,
                        null
                ));
    }

}