package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class VehicleDAO {
    private Session session;

    public VehicleDAO(Session session) {
        this.session = session;
    }

    public void create(Vehicle vehicle) {
        Transaction transaction = session.beginTransaction();
        session.save(vehicle);
        transaction.commit();
    }

    public Optional<Vehicle> read(int id) {
        Vehicle vehicle = session.get(Vehicle.class, id);
        return Optional.ofNullable(vehicle);
    }

    public List<Vehicle> readAll() {
        return session.createQuery("FROM Vehicle", Vehicle.class).list();
    }

    public void update(Vehicle vehicle) {
        Transaction transaction = session.beginTransaction();
        session.update(vehicle);
        transaction.commit();
    }

    public void delete(int id) {
        Transaction transaction = session.beginTransaction();
        Vehicle vehicle = session.get(Vehicle.class, id);
        if (vehicle != null) {
            session.delete(vehicle);
        }
        transaction.commit();
    }

    public List<Vehicle> readByShowroom(CarShowroom showroom) {
        try {
            Transaction transaction = session.beginTransaction();
            List<Vehicle> vehicles = session.createQuery("FROM Vehicle WHERE carShowroom = :showroom", Vehicle.class)
                    .setParameter("showroom", showroom)
                    .getResultList();
            transaction.commit();
            return vehicles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
