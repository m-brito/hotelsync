package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;

import java.util.List;

public interface GuestDao extends Dao<Long, Guest> {
    List<Guest> findAllByIdReservation(Long id);
}
