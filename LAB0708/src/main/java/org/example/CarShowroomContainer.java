package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarShowroomContainer {
    Map<String, CarShowroom> salony = new HashMap<>();

    public void addCenter(String nazwa, double pojemnosc){
        salony.put(nazwa,new CarShowroom(nazwa,pojemnosc));
    }

    public void removeCenter(String nazwa) {
        salony.remove(nazwa);
    }

    public List<String> findEmpty() {
        List<String> lista = new ArrayList<>();
        for ( Map.Entry<String, CarShowroom> s : salony.entrySet()) {
            if (s.getValue().getListaSamochodow().isEmpty()) {
                lista.add(s.getValue().getNazwaSalonu());
            }
        }
        return lista;
    }

    public void summary() {
        double zapelnienie;
        for ( Map.Entry<String, CarShowroom> s : salony.entrySet()) {
            zapelnienie = (s.getValue().getListaSamochodow().size() / s.getValue().getMaksymalnaPojemnoscSalonu()) * 100;
            System.out.println(s.getKey() + " " + zapelnienie + "%");
        }
    }

    public Map<String, CarShowroom> getSalony() {
        return salony;
    }

    public void setSalony(Map<String, CarShowroom> salony) {
        this.salony = salony;
    }
}
