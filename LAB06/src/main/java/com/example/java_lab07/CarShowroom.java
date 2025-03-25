package com.example.java_lab07;

import java.util.*;

public class CarShowroom implements Comparator<Vehicle>{
    String nazwaSalonu;
    int maksPojemnoscSalonu;
    List<Vehicle> lista;
    Map<String, Integer> iloscMarki;

    CarShowroom(String nazwaSalonu, int maksPojemnoscSalonu){
        this.nazwaSalonu = nazwaSalonu;
        this.maksPojemnoscSalonu = maksPojemnoscSalonu;
        this.lista = new LinkedList<>();
        this.iloscMarki = new HashMap<>();
    }

    public int containsAndSumCar(String marka, String model) {
        int sum = 0;
        for (Vehicle item : lista){
            if (item.marka.equals(marka) && item.model.equals(model)) {
                sum++;
            }
        }
        return sum;
    }
    public String getNazwaSalonu() {
        return nazwaSalonu;
    }

    public int getMaksPojemnoscSalonu() {
        return maksPojemnoscSalonu;
    }

    public List<Vehicle> getLista() {
        return lista;
    }

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        int cmp = o1.marka.compareTo(o2.marka); //jeśli sa równe to zwraca 0, jak pierwszy wieskzy to 1 itd
        if (cmp == 0) {
            return o1.model.compareTo(o2.model);
        }
        return cmp;
    }
    public void addProduct(Vehicle vehicle){
        int sum = containsAndSumCar(vehicle.marka, vehicle.model);
        System.out.println("Ilosc posiadanych aut o tym samym modelu i marce: " + sum);

        if(lista.size() < maksPojemnoscSalonu){
            lista.add(vehicle);
            System.out.println("Dodano samochod: " + vehicle.marka + " " + vehicle.model);
        }
        else{
            System.err.println("Brak miejsca w salonie");
        }
    }

    public void getProduct(Vehicle vehicle){
        if (lista.contains(vehicle)){
            String nazwaUsunieta = vehicle.marka + " " + vehicle.model;
            this.lista.remove(vehicle);
            System.out.println("Usunieto samochod: " + nazwaUsunieta);
        }
        else{
            System.out.println("Brak podanego produktu w bazie");
        }
    }

    public void removeProduct(String marka, String model){
        String nazwaUsunieta = marka + " " + model;
        lista.removeIf(item -> item.marka.equals(marka) && item.model.equals(model));
        System.out.println("Usunieto wszystkie smaochody typu: " + nazwaUsunieta);
    }

    public void search(Vehicle vehicle){
        for(Vehicle item: lista){
            int check = compare(vehicle, item);
            if(check == 0){
                vehicle.printPrint();
            }
        }
    }

    public List<Vehicle> searchPartial(String partialString) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle item : lista) {
            if (item.getNazwa().toLowerCase().contains(partialString.toLowerCase())) {
                vehicles.add(item);
            }
        }
        return vehicles;
    }
    public int countByCondition(ItemCondition stan){
        int ilosc = 0;
        for(Vehicle item: lista){
            if(item.stan.equals(stan))
                ilosc++;
        }
        System.out.println("Ilość samochodów stanu " + stan + ": " + ilosc);
        return ilosc;
    }

    public void summary(){
        for(Vehicle item : lista){
            item.printPrint();
        }
    }

    public List<String> sortByName() { //sprawdzić skrócona wersje zwracajajca sama liste
        Collections.sort(lista);
        List<String> listaPosortowana = new LinkedList<>();
        for(Vehicle item : lista){
            listaPosortowana.add(item.print());
            listaPosortowana.add("\n");
        }
        System.out.println("Posortowana lista: " + listaPosortowana);
        return listaPosortowana;
    }

    public int licznikMarki(String marka) {
        int licznik = 0;
        for (Vehicle vehicle : lista) {
            if (vehicle.marka.equalsIgnoreCase(marka)) {
                licznik++;
            }
        }
        return licznik;

    }
    public void updateBrandCountMap() {
        for (Vehicle item : lista) {
            int ilosc = licznikMarki(item.marka);
            iloscMarki.put(item.marka, ilosc);
        }
    }

    public List<String> sortByAmount() {
        updateBrandCountMap();
        Comparator<Vehicle> comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (iloscMarki.get(o1.marka) > iloscMarki.get(o2.marka)) {
                    return -1;
                } else if (iloscMarki.get(o1.marka) < iloscMarki.get(o2.marka)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(lista, comparator);
        List<String> listaPosortowana = new ArrayList<>();
        lista.forEach(s -> listaPosortowana.add(s.print()));
        listaPosortowana.add("\n");

        System.out.println("Posortowana lista iloscia: " + listaPosortowana);
        return listaPosortowana;
    }

    public String max() {
        updateBrandCountMap();
        StringBuilder stringBuilder = new StringBuilder();  //super string który mozna na biezaco modyfikowac
        int m = Collections.max(iloscMarki.values());
        for (Map.Entry<String, Integer> car : iloscMarki.entrySet()) {
            if (car.getValue() == m) {
                stringBuilder.append(car.getKey()).append(" : ").append(car.getValue()).append("\n");
            }
        }
        System.out.println("Najwięcej jest: " + stringBuilder.toString());
        return stringBuilder.toString();
    }
}

