package com.company;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>{
    String marka;
    String model;
    ItemCondition stan;
    double cena;
    int rokProdukcji;
    double przebieg;
    double pojemnoscSilnika;

    public String getNazwa() {
        return marka + " " + model;
    }
    Vehicle(String marka, String model, ItemCondition stan, double cena, int rokProdukcji, double przebieg,
            double pojemnoscSilnika){
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    @Override
    public int compareTo(Vehicle o) {
        int marka = this.marka.compareTo(o.marka);
        if (marka == 0){
            return this.model.compareTo(o.model);
        }else{
            return marka;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(marka, vehicle.marka) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka, model);
    }

    public String print() {
        return "\n Vehicle{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", stan=" + stan +
                ", cena=" + cena +
                ", rokProdukcji=" + rokProdukcji +
                ", przebieg=" + przebieg +
                ", pojemnoscSilnika=" + pojemnoscSilnika +
                '}';
    }

    void printPrint(){
            System.out.println(this.print());
    }

}
