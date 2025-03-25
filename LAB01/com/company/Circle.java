package com.company;

public class Circle extends Figure implements Printable{
    double radius;
    double circleArea;
    double circlePerimeter;

    Circle(double radius){
        if(radius <= 0){
            throw new IllegalArgumentException("Nie istnieje ujemna wartosc promienia\n");
        }
        this.radius = radius;
        quantity ++;
    }
    @Override
    double calculatePerimeter() {
        circlePerimeter = 2 * Math.PI * radius;
        return circlePerimeter;
    }

    @Override
    double calculateArea() {
        circleArea = Math.PI * Math.pow(radius, 2);
        return circleArea;
    }

    @Override
    public void print() {
        System.out.println("Dane kola: ");
        System.out.println("Dlugosc promienia: " + radius);
        System.out.println("Obwod: " + calculatePerimeter());
        System.out.println("Pole: " + calculateArea());
        System.out.println(" ");
    }

}
