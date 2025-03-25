package com.example.java_lab07;

public class DataGenerator {
    private static DataGenerator instance;

    private DataGenerator() {
    }

    public static DataGenerator getInstance() {
        if (instance == null) {
            instance = new DataGenerator();
        }
        return instance;
    }

    public void generateData(CarShowroomContainer container) {
        // Tworzenie przykładowych salonów samochodowych
        CarShowroom showroom1 = new CarShowroom("Salon Warszawa", 100);
        CarShowroom showroom2 = new CarShowroom("Salon Kraków", 150);
        CarShowroom showroom3 = new CarShowroom("Salon Wrocław", 200);

        // Dodawanie salonów do kontenera
        container.addCenter(showroom1);
        container.addCenter(showroom2);
        container.addCenter(showroom3);

        // Dodawanie przykładowych pojazdów do salonów
        showroom1.addProduct(new Vehicle("Ford", "Focus", ItemCondition.USED, ItemAvailability.AVAILABLE, 15000.0, 2018, 25000.0, 2.0, showroom1));
        showroom1.addProduct(new Vehicle("Ford", "Focus", ItemCondition.USED,ItemAvailability.AVAILABLE, 15000, 2018, 25000, 2.0, showroom1));
        showroom1.addProduct(new Vehicle("Toyota", "Corolla", ItemCondition.USED,ItemAvailability.AVAILABLE, 15000.0, 2018, 50000.0, 1.8, showroom1));
        showroom1.addProduct(new Vehicle("Honda", "Civic", ItemCondition.USED,ItemAvailability.AVAILABLE, 12000.0, 2017, 60000.0, 1.5,showroom1));
        showroom1.addProduct(new Vehicle("Ford", "Focus", ItemCondition.NEW, ItemAvailability.AVAILABLE,25000.0, 2022, 1000.0, 1.6, showroom1));
        showroom1.addProduct(new Vehicle("Chevrolet", "Malibu", ItemCondition.USED, ItemAvailability.AVAILABLE,18000.0, 2019, 40000.0, 2.0, showroom1));

        showroom2.addProduct(new Vehicle("Skoda", "Octavia", ItemCondition.NEW, ItemAvailability.AVAILABLE,22000, 2021, 0, 2.0, showroom2));
        showroom2.addProduct(new Vehicle("BMW", "3 Series", ItemCondition.USED, ItemAvailability.AVAILABLE,20000.0, 2019, 30000.0, 2.0, showroom2));
        showroom2.addProduct(new Vehicle("Mercedes-Benz", "C-Class", ItemCondition.NEW, ItemAvailability.AVAILABLE,35000.0, 2023, 100.0, 2.0, showroom2));
        showroom2.addProduct(new Vehicle("Audi", "A4", ItemCondition.USED, ItemAvailability.AVAILABLE,22000.0, 2020, 25000.0, 2.0, showroom2));
        showroom2.addProduct(new Vehicle("Volkswagen", "Golf", ItemCondition.DAMAGED, ItemAvailability.AVAILABLE,15000.0, 2017, 70000.0, 1.4, showroom2));
        showroom2.addProduct(new Vehicle("Subaru", "Impreza", ItemCondition.USED, ItemAvailability.AVAILABLE,18000.0, 2018, 40000.0, 2.0, showroom2));
        showroom2.addProduct(new Vehicle("Kia", "Rio", ItemCondition.NEW, ItemAvailability.AVAILABLE,19000.0, 2023, 10.0, 1.6, showroom2));
        showroom2.addProduct(new Vehicle("Toyota", "Corolla", ItemCondition.NEW, ItemAvailability.AVAILABLE,28000.0, 2024, 0.0, 1.8, showroom2));
    }
}
