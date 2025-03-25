package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Vehicle implements Comparable<Vehicle>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String marka;
    String model;
    //@Transient
    ItemCondition stan;
    double cena;
    Integer rokProdukcji;
    double przebieg;
    double pojemnoscSilnika;
    @ManyToOne
    @JoinColumn(name = "carShowroom_id")
    private CarShowroom carShowroom;

    public CarShowroom getCarShowroom() {
        return carShowroom;
    }

    public void setCarShowroom(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }
    public Vehicle(){}

    public Vehicle(String marka, String model,
                   ItemCondition stan, double cena, Integer rokProdukcji,
                   double przebieg, double pojemnoscSilnika) {
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public String print() {
        return "\nVehicle{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", stan=" + stan +
                ", cena=" + cena +
                ", rokProdukcji=" + rokProdukcji +
                ", przebieg=" + przebieg +
                ", pojemnoscSilnika=" + pojemnoscSilnika +
                '}';
    }

    public String nazwa(){
        return marka + " " + model;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ItemCondition getStan() {
        return stan;
    }

    public void setStan(ItemCondition stan) {
        this.stan = stan;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Integer getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(Integer rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public double getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(double przebieg) {
        this.przebieg = przebieg;
    }

    public double getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }

    public void setPojemnoscSilnika(double pojemnoscSilnika) {
        this.pojemnoscSilnika = pojemnoscSilnika;
    }
}
