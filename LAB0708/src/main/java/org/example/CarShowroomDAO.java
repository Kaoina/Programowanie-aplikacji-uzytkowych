package org.example;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CarShowroomDAO {

    private Session session;

    public CarShowroomDAO(Session session) {
        this.session = session;
    }

    public void create(CarShowroom showroom) {
        Transaction transaction = session.beginTransaction();
        session.save(showroom);
        transaction.commit();
    }

    public Optional<CarShowroom> read(int id) {
        CarShowroom showroom = session.get(CarShowroom.class, id);
        return Optional.ofNullable(showroom);
    }

    public List<CarShowroom> readAll() {
        return session.createQuery("FROM CarShowroom", CarShowroom.class).list();
    }

    public void update(CarShowroom showroom) {
        Transaction transaction = session.beginTransaction();
        session.update(showroom);
        transaction.commit();
    }

    public void delete(int id) {
        Transaction transaction = session.beginTransaction();
        CarShowroom showroom = session.get(CarShowroom.class, id);
        if (showroom != null) {
            session.delete(showroom);
        }
        transaction.commit();
    }

    public Optional<CarShowroom> readByName(String nazwa) {
        Query<CarShowroom> query = session.createQuery("FROM CarShowroom WHERE nazwaSalonu = :nazwa", CarShowroom.class);
        query.setParameter("nazwa", nazwa);
        return Optional.ofNullable(query.uniqueResult());
        //return query.uniqueResultOptional();
    }
}
