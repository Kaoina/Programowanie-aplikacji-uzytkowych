package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.example.HibernateUtil;
import org.hibernate.Session;
public class CarShowroomSerializer {

    public void saveShowrooms(List<CarShowroom> showrooms, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(showrooms);
            System.out.println("Salony zostały zapisane do pliku " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CarShowroom> loadShowrooms(String fileName) {
        List<CarShowroom> showrooms = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                showrooms = (List<CarShowroom>) ois.readObject();

                session.beginTransaction();
                for (CarShowroom showroom : showrooms) {
                    showroom = (CarShowroom) session.merge(showroom);
                    Hibernate.initialize(showroom.getListaSamochodow());
                }
                session.getTransaction().commit();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return showrooms;
    }


}
