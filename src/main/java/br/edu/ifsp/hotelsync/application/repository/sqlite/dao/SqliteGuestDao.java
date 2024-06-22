package br.edu.ifsp.hotelsync.application.repository.sqlite.dao;

import br.edu.ifsp.hotelsync.application.repository.sqlite.ConnectionFactory;
import br.edu.ifsp.hotelsync.domain.entities.guest.*;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SqliteGuestDao implements GuestDao {

    @Override
    public Long save(Guest guest) {
        String sql = "INSERT INTO Guest(name, pronouns, birthdate, phone, cpf, road, city, state, cep, district, complement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getPronouns());
            stmt.setObject(3, guest.getBirthdate());
            stmt.setString(4, guest.getPhone().getValue());
            stmt.setString(5, guest.getCpf().getValue());
            stmt.setString(6, guest.getAddress().getRoad());
            stmt.setString(7, guest.getAddress().getCity());
            stmt.setString(8, guest.getAddress().getState().name());
            stmt.setString(9, guest.getAddress().getCep());
            stmt.setString(10, guest.getAddress().getDistrict());
            stmt.setString(11, guest.getAddress().getComplement());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Guest guest) {
        String sql = "UPDATE Guest SET name = ?, pronouns = ?, birthdate = ?, phone = ?, cpf = ?, road = ?, city = ?, state = ?, cep = ?, district = ?, complement = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getPronouns());
            stmt.setObject(3, guest.getBirthdate());
            stmt.setString(4, guest.getPhone().toString());
            stmt.setString(5, guest.getCpf().toString());
            stmt.setString(6, guest.getAddress().getRoad());
            stmt.setString(7, guest.getAddress().getCity());
            stmt.setString(8, guest.getAddress().getState().name());
            stmt.setString(9, guest.getAddress().getCep());
            stmt.setString(10, guest.getAddress().getDistrict());
            stmt.setString(11, guest.getAddress().getComplement());
            stmt.setLong(12, guest.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Guest> findOneByKey(Long id) {
        String sql = "SELECT * FROM Guest WHERE id = ?";
        Guest guest = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                guest = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(guest);
    }

    @Override
    public boolean existsByKey(Long id) {
        String sql = "SELECT count(*) FROM Guest WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Long, Guest> findAll() {
        String sql = "SELECT * FROM Guest";
        Map<Long, Guest> guests = new HashMap<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Guest guest = resultSetToEntity(rs);
                guests.put(guest.getId(), guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    @Override
    public List<Guest> findAllByIdReservation(Long id) {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT g.* FROM Guest g JOIN GuestReservation gr ON g.id = gr.guestId WHERE gr.reservationId = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Guest guest = resultSetToEntity(rs);
                guests.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    @Override
    public void deleteByKey(Long id) {
        String sql = "DELETE FROM Guest WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Guest resultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String pronouns = resultSet.getString("pronouns");
        String stringBirthdate = resultSet.getString("birthdate");
        LocalDate birthdate = LocalDate.parse(stringBirthdate);
        String phone = resultSet.getString("phone");
        String cpf = resultSet.getString("cpf");

        String road = resultSet.getString("road");
        String city = resultSet.getString("city");
        String state = resultSet.getString("state");
        String cep = resultSet.getString("cep");
        String district  = resultSet.getString("district");
        String complement  = resultSet.getString("complement");

        Address address = new Address(road, city, State.valueOf(state), cep, district, complement);

        return Guest.createOwnerWithId(id, name, pronouns, birthdate, new Phone(phone), new Cpf(cpf), address);
    }
}