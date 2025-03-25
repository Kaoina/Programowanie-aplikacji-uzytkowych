package com.example.java_lab07;

import java.util.*;

public class CarShowroomContainer {
    public CarShowroomContainer() {
        this.salony = salony;
    }
    public Map<String, CarShowroom> getSalony() {
        return salony;
    }

    Map<String, CarShowroom> salony = new TreeMap<String, CarShowroom>();

    void addCenter(CarShowroom carShowroom) {
        if (salony.containsKey(carShowroom.nazwaSalonu)) {
            System.err.println("Salon o nazwie " + carShowroom.nazwaSalonu + " już istnieje");
        }
        salony.put(carShowroom.nazwaSalonu,  carShowroom);
    }

    void removeCenter(String nazwa) {
        if (salony.containsKey(nazwa)) {
            salony.remove(nazwa);
            System.out.println("Usunięto salon: " + nazwa);
        } else {
            System.err.println("Salon o nazwie " + nazwa + " nie istnieje");
        }
    }

    List<String> findEmpty() {
        List<String> pusteSalony = new ArrayList<String>();
        for (Map.Entry<String, CarShowroom> s : salony.entrySet()) {
            if (s.getValue().getLista().isEmpty()) {
                pusteSalony.add(s.getValue().getNazwaSalonu());
            }
        }
        System.out.println("Puste salony: " + pusteSalony);
        return pusteSalony;
    }

    void summary() {
        double zapelnienie;
        for (Map.Entry<String, CarShowroom> s : salony.entrySet()) {
            zapelnienie = ((double) (s.getValue().getLista().size()) /(s.getValue().getMaksPojemnoscSalonu()))*100;
            System.out.println("Salon " + s.getKey() + " jest zapelniony w " + zapelnienie + "%");
        }
    }

    void getAllCars(){
        System.out.println("Posiadane auta: ");
        for(Map.Entry<String, CarShowroom> entry : salony.entrySet()){ //entry to reprezentacja całego wpisu
            entry.getValue().getLista().forEach(s -> s.printPrint());
        }

    }

    public List<CarShowroom> sortCentersByLoad() {
        List<CarShowroom> sortedList = new ArrayList<>(salony.values());
        Collections.sort(sortedList, new Comparator<CarShowroom>() {
            @Override
            public int compare(CarShowroom s1, CarShowroom s2) {
                double load1 = (double) s1.getLista().size() / s1.getMaksPojemnoscSalonu();
                double load2 = (double) s2.getLista().size() / s2.getMaksPojemnoscSalonu();
                return Double.compare(load1, load2); // Sortowanie od najmniej do najbardziej obciążonego
            }
        });
        return sortedList;
    }

}
