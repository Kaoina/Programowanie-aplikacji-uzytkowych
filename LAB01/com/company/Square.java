package com.company;

public class Square extends Figure implements Printable{

    double  edgeLength;
    double squareArea;
    double squarePerimeter;
    Square(double edgeLength){
        if (edgeLength <= 0) {
            //konstrukcja do ręcznego wyrzucania wyjątku, tworzymy nowy obiekt IllArg z argumentem wiadomością
            throw new IllegalArgumentException("Nie istnieje ujemna wartosc boku\n");
        }
        this.edgeLength = edgeLength;
        quantity++;
    }
    @Override
    double calculateArea() {
        squareArea = edgeLength * edgeLength;
        return  squareArea;
    }

    @Override
    double calculatePerimeter() {
        squarePerimeter = edgeLength * 4;
        return squarePerimeter;
    }

    @Override
    public void print() {
        System.out.println("Dane kwadratu: ");
        System.out.println("Dlugosc boku: " + edgeLength);
        System.out.println("Obwod: " + calculatePerimeter());
        System.out.println("Pole: " + calculateArea());
        System.out.println(" ");
    }
}
