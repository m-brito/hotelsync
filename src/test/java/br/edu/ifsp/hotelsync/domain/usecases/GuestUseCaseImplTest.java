package br.edu.ifsp.hotelsync.domain.usecases;

import br.edu.ifsp.hotelsync.domain.entities.guest.Cpf;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestUseCaseImplTest {

    @Mock
    GuestDao repository;

    @Nested
    @DisplayName("Create Guest")
    class CreateGuestTest {

        @Test
        @DisplayName("when successfully creating a guest")
        public void createValid(){
            Guest guest = Guest.createGuest("Guest Name", LocalDate.now(), new Cpf("123.456.789-00"));
            when(repository.save(any())).thenReturn(1L);

            Long savedGuestId = repository.save(guest);
            assertNotNull(savedGuestId);
        }

        @Test
        @DisplayName("when failing to create a guest")
        public void createInvalid(){
            assertThrows(IllegalArgumentException.class, () -> Guest.createGuest("", LocalDate.now(), new Cpf("123.456.789-00")));
        }
    }

    @Nested
    @DisplayName("Find Guest")
    public class FindGuestTest{

        @Test
        public void findValid(){
            Guest guest = Guest.createGuest("Guest Name", LocalDate.now(), new Cpf("123.456.789-00"));
            when(repository.findOneByKey(any())).thenReturn(Optional.of(guest));

            Optional<Guest> foundGuest = repository.findOneByKey(1L);
            assertTrue(foundGuest.isPresent());
            assertEquals(guest, foundGuest.get());
        }

        @Test
        public void findInvalid(){
            when(repository.findOneByKey(any())).thenReturn(null);

            Optional<Guest> foundGuest = repository.findOneByKey(1L);
            assertNull(foundGuest);
        }
    }

    @Nested
    @DisplayName("Update Guest")
    public class UpdateGuestTest{

        @Test
        @DisplayName("when successfully updating a guest")
        public void updateValid(){
            Guest guest = Guest.createGuest("Guest Name", LocalDate.now(), new Cpf("123.456.789-00"));
            doNothing().when(repository).update(any());
            when(repository.findOneByKey(guest.getId())).thenReturn(Optional.of(guest));

            repository.update(guest);
            Optional<Guest> updatedGuest = repository.findOneByKey(guest.getId());
            assertTrue(updatedGuest.isPresent());
            assertEquals(guest, updatedGuest.get());
        }

        @Test
        @DisplayName("when failing to update a guest")
        public void updateInvalid(){
            assertThrows(IllegalArgumentException.class, () -> {
                Guest guest = Guest.createGuest("", LocalDate.now(), new Cpf("123.456.789-00"));
                repository.update(guest);
            });
        }
    }
}