package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class CarShowroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nazwaSalonu;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vehicle> listaSamochodow = new ArrayList<>();
    private double maksymalnaPojemnoscSalonu;

    @Transient
    private Map<String, Integer> iloscDanegoSamochodu = new HashMap<>();


    public CarShowroom(){}

    public CarShowroom(String nazwa, double pojemnosc) {
        this.nazwaSalonu = nazwa;
        this.maksymalnaPojemnoscSalonu = pojemnosc;
    }

    public double getMaksymalnaPojemnoscSalonu() {
        return maksymalnaPojemnoscSalonu;
    }

    public List<Vehicle> getListaSamochodow() {
        return listaSamochodow;
    }

    public String getNazwaSalonu() {
        return nazwaSalonu;
    }

    public void addProduct(Vehicle vehicle) {
        if (listaSamochodow.size() < maksymalnaPojemnoscSalonu){
            int val = 0;
            if (iloscDanegoSamochodu.containsKey(vehicle.nazwa())){
                val = iloscDanegoSamochodu.get(vehicle.nazwa());
            }
            listaSamochodow.add(vehicle);
            iloscDanegoSamochodu.put(vehicle.nazwa(),val + 1);
        } else {
            System.err.println("Przekorczona maksymalna pojemnosc salonu");
        }
    }

    public void getProduct(Vehicle vehicle) {
        if (listaSamochodow.contains(vehicle)){
            listaSamochodow.remove(vehicle);
            if (iloscDanegoSamochodu.get(vehicle.nazwa()) > 1) {
                iloscDanegoSamochodu.put(vehicle.nazwa(),
                        iloscDanegoSamochodu.get(vehicle.nazwa()) - 1);
            } else {
                iloscDanegoSamochodu.remove(vehicle.nazwa());
            }
        } else {
            System.err.println("Podany samochod, czyli " + vehicle.print() + " nie istnieje");
        }
    }

    public void removeProduct(String nazwa) {
        listaSamochodow.removeIf(v -> v.nazwa().equals(nazwa));
        iloscDanegoSamochodu.remove(nazwa);
    }

    public List<String> search(String nazwa) {
        List<String> vehicles = new ArrayList<>();
        for (Vehicle v : listaSamochodow){
            if (v.nazwa().equals(nazwa)){
                vehicles.add(v.print());
            }
        }
        return vehicles;
    }

    public List<String> searchPartial(String partialString) {
        List<String> vehicles = new ArrayList<>();
        for (Vehicle v : listaSamochodow){
            if (v.print().contains(partialString)){
                vehicles.add(v.print());
            }
        }
        return vehicles;
    }

    public int countByCondition(ItemCondition itemCondition) {
        int i = 0;
        for (Vehicle v : listaSamochodow) {
            if (v.stan == itemCondition) i++;
        }
        return i;
    }

    public void summary() {
        for (Vehicle v : listaSamochodow) {
            v.nazwa();
        }
    }

    public List<String> sortByName() {
        Collections.sort(listaSamochodow);
        List<String> listaSamochodowPosortowana = new ArrayList<>();
        listaSamochodow.forEach(s -> listaSamochodowPosortowana.add(s.print()));
        listaSamochodowPosortowana.add("\n");
        return listaSamochodowPosortowana;
    }

    public List<String> sortByAmount() {
        Comparator<Vehicle> comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (iloscDanegoSamochodu.get(o1.nazwa()) > iloscDanegoSamochodu.get(o2.nazwa())) {
                    return -1;
                } else if (iloscDanegoSamochodu.get(o1.nazwa()) < iloscDanegoSamochodu.get(o2.nazwa())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(listaSamochodow, comparator);
        List<String> listaSamochodowPosortowana = new ArrayList<>();
        listaSamochodow.forEach(s -> listaSamochodowPosortowana.add(s.print()));
        listaSamochodowPosortowana.add("\n");
        return listaSamochodowPosortowana;
    }

    public String max() {
        StringBuilder stringBuilder = new StringBuilder();
        int m = Collections.max(iloscDanegoSamochodu.values());
        for (Map.Entry<String, Integer> car : iloscDanegoSamochodu.entrySet()) {
            if (car.getValue() == m) {
                stringBuilder.append(car.getKey()).append(" : ").append(car.getValue()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String min() {
        StringBuilder stringBuilder = new StringBuilder();
        int m = Collections.min(iloscDanegoSamochodu.values());
        for (Map.Entry<String, Integer> car : iloscDanegoSamochodu.entrySet()) {
            if (car.getValue() == m) {
                stringBuilder.append(car.getKey()).append(" : ").append(car.getValue()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNazwaSalonu(String nazwaSalonu) {
        this.nazwaSalonu = nazwaSalonu;
    }

    public void setListaSamochodow(List<Vehicle> listaSamochodow) {
        this.listaSamochodow = listaSamochodow;
    }

    public void setMaksymalnaPojemnoscSalonu(double maksymalnaPojemnoscSalonu) {
        this.maksymalnaPojemnoscSalonu = maksymalnaPojemnoscSalonu;
    }

    public Map<String, Integer> getIloscDanegoSamochodu() {
        return iloscDanegoSamochodu;
    }

    public void setIloscDanegoSamochodu(Map<String, Integer> iloscDanegoSamochodu) {
        this.iloscDanegoSamochodu = iloscDanegoSamochodu;
    }
}
