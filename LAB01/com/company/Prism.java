package com.company;

public class Prism implements Printable{
    Figure base;
    double prismHeight;
    double prismArea;
    double prismVolume;

    Prism(double prismHeight, Figure base){
        if(prismHeight <= 0 || base == null){
          throw new IllegalArgumentException("Nie istnieje ujemna wartosc wysokosci/podstawy\n");
        }
        this.prismHeight = prismHeight;
        this.base = base;
    }
    double calculatePrismArea(){
        prismArea = 2 * base.calculateArea() + (base.calculatePerimeter() * prismHeight);
        return prismArea;
    }
    double calculatePrismVolume(){
        prismVolume = base.calculatePerimeter() * prismHeight;
        return prismVolume;
    }
    @Override
    public void print() {
        System.out.println("Dane graniastoslupa: ");
        //getClass znajdzie do jakiej klasy nalezy dany obiekt
        //getSimpleName poda nazwę tej klasy
        System.out.println("Podstawa: " + base.getClass().getSimpleName());
        System.out.println("Wysokosc: " + prismHeight );
        System.out.println("Pole powierzchni: " + calculatePrismArea());
        System.out.println("Objetosc: " + calculatePrismVolume());
        System.out.println(" ");
    }
}
