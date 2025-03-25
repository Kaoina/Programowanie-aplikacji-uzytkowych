package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarShowroomCSV {

    public static void exportShowroomToCSV(CarShowroom showroom, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Zapisz nagłówek pliku CSV
            writer.append("Salon, Maksymalna pojemność\n");
            writer.append(showroom.getNazwaSalonu() + ", " + showroom.getMaksymalnaPojemnoscSalonu() + "\n");

            // Zapisz nagłówek dla pojazdów
            writer.append("Marka, Model, Rok Produkcji, Przebieg, Cena, Pojemność Silnika\n");

            // Zapisz dane pojazdów
            for (Vehicle vehicle : showroom.getListaSamochodow()) {
                writer.append(vehicle.getMarka() + ", " +
                        vehicle.getModel() + ", " +
                        vehicle.getRokProdukcji() + ", " +
                        vehicle.getPrzebieg() + ", " +
                        vehicle.getCena() + ", " +
                        vehicle.getPojemnoscSilnika() + "\n");
            }

            System.out.println("Eksport salonu do pliku CSV zakończony sukcesem: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CarShowroom importShowroomFromCSV(String fileName) {
        CarShowroom showroom = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Odczytaj nagłówek i informacje o salonie
            reader.readLine(); // Pomiń nagłówek
            String[] showroomData = reader.readLine().split(", ");
            String nazwa = showroomData[0];
            double pojemnosc = Double.parseDouble(showroomData[1]);
            showroom = new CarShowroom(nazwa, pojemnosc);

            // Odczytaj nagłówek i informacje o pojazdach
            reader.readLine(); // Pomiń nagłówek pojazdów

            String line;
            List<Vehicle> vehicles = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] vehicleData = line.split(", ");
                String marka = vehicleData[0];
                String model = vehicleData[1];
                Integer rokProdukcji = Integer.parseInt(vehicleData[2]);
                double przebieg = Double.parseDouble(vehicleData[3]);
                double cena = Double.parseDouble(vehicleData[4]);
                double pojemnoscSilnika = Double.parseDouble(vehicleData[5]);

                Vehicle vehicle = new Vehicle(marka, model, null, cena, rokProdukcji, przebieg, pojemnoscSilnika);
                vehicle.setStan(ItemCondition.NEW); // Wszystkie pojazdy jako nowe
                vehicles.add(vehicle);
            }

            showroom.setListaSamochodow(vehicles);

            System.out.println("Import salonu z pliku CSV zakończony sukcesem: " + fileName);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return showroom;
    }



}
