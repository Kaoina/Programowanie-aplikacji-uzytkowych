package com.company;

public class Main {
    public static void main(String[] args) {
        Vehicle car1 = new Vehicle("Toyota", "Corolla", ItemCondition.USED, 15000.0, 2018, 50000.0, 1.8);
        Vehicle car2 = new Vehicle("Honda", "Civic", ItemCondition.USED, 12000.0, 2017, 60000.0, 1.5);
        Vehicle car3 = new Vehicle("Ford", "Focus", ItemCondition.NEW, 25000.0, 2022, 1000.0, 1.6);
        Vehicle car4 = new Vehicle("Chevrolet", "Malibu", ItemCondition.USED, 18000.0, 2019, 40000.0, 2.0);
        Vehicle car5 = new Vehicle("BMW", "3 Series", ItemCondition.USED, 20000.0, 2019, 30000.0, 2.0);
        Vehicle car6 = new Vehicle("Mercedes-Benz", "C-Class", ItemCondition.NEW, 35000.0, 2023, 100.0, 2.0);
        Vehicle car7 = new Vehicle("Audi", "A4", ItemCondition.USED, 22000.0, 2020, 25000.0, 2.0);
        Vehicle car8 = new Vehicle("Volkswagen", "Golf", ItemCondition.DAMAGED, 15000.0, 2017, 70000.0, 1.4);
        Vehicle car9 = new Vehicle("Subaru", "Impreza", ItemCondition.USED, 18000.0, 2018, 40000.0, 2.0);
        Vehicle car10 = new Vehicle("Kia", "Rio", ItemCondition.NEW, 19000.0, 2023, 10.0, 1.6);
        Vehicle car11 = new Vehicle("Toyota", "Corolla", ItemCondition.NEW, 28000.0, 2024, 0.0, 1.8);
        Vehicle car12 = new Vehicle("Honda", "Civic", ItemCondition.USED, 10000.0, 2016, 80000.0, 1.8);
        Vehicle car13 = new Vehicle("Honda", "Civic", ItemCondition.USED, 10000.0, 2016, 80000.0, 1.8);
        //Dodanie salonu
        CarShowroomContainer container = new CarShowroomContainer();
        CarShowroom showroom1 = new CarShowroom("Salon1", 10);
        CarShowroom showroom2 = new CarShowroom("Salon2", 15);
        CarShowroom showroom3 = new CarShowroom("Salon3", 20);

        showroom1.addProduct(car1);
        showroom1.addProduct(car13);
        showroom1.addProduct(car2);
        showroom1.addProduct(car3);
        showroom1.addProduct(car11);
        showroom1.addProduct(car12);
        showroom1.addProduct(car4);
        showroom2.addProduct(car8);
        showroom2.addProduct(car9);
        showroom2.addProduct(car10);
        showroom2.addProduct(car4);
        showroom2.addProduct(car6);
        showroom2.addProduct(car7);
        showroom2.addProduct(car5);
        System.out.println(" ");

        container.addCenter(showroom1);
        container.addCenter(showroom2);
        container.addCenter(showroom3);

        //Wyświetlanie informacji o samochodzie
//        car1.printPrint();

        //Usuwanie jednego samochodu
//        showroom1.getProduct(car1);
//        car1.print();

        //Usuwanie wszystkich samochodów danej marki
//        showroom1.summary();
//        showroom1.removeProduct("Honda", "Civic");
//        showroom1.summary();

        //Zwracanie produktów o danej nazwie
//        showroom2.search(car10);

        //Fragment nazwy produktu i zwraca produkty pasujące
//        showroom2.searchPartial("Impre");

        //Zwraca ilość produktów o danym stanie
//        showroom1.countByCondition(ItemCondition.USED);

        //Inofroamcje o wsysztkiich produktoach w salonie
//        showroom1.summary();

        //Posrotowana liczba poroduktów alfabetycznie
//        showroom1.sortByName();

        //Posortowana lista produktów po ilości – malejąco
//        showroom1.sortByAmount();

        //Zwraca produkt którego jest najwięcej
//        showroom1.max();

        //Zapelnienie salonu
//        container.summary();

        //Usuniecie salonu
//        container.removeCenter("Salon3");

        //Znalezenie pustego
//        container.findEmpty();

        //Wypisanie wszyyyyystkich aut
//        container.getAllCars();

    }
}
