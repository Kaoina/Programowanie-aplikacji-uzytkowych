package com.company;

public class Triangle extends Figure implements Printable{
    double firstEdgeLength;
    double secondEdgeLength;
    double thirdEdgeLength;
    double triangleArea;
    double trianglePerimeter;

    Triangle(double firstEdgeLength, double secondEdgeLength, double thirdEdgeLength){
        if(firstEdgeLength <= 0 || secondEdgeLength <=0 || thirdEdgeLength <= 0){
            throw new IllegalArgumentException("Nie istnieją ujemne wartości boków\n");
        }
        if(firstEdgeLength + secondEdgeLength < thirdEdgeLength ||
           firstEdgeLength + thirdEdgeLength < secondEdgeLength ||
           secondEdgeLength + thirdEdgeLength < firstEdgeLength){
           throw new IllegalArgumentException("Nie możliwe stworzenie trójkąta z tych wartości\n");
        }
        this.firstEdgeLength = firstEdgeLength;
        this.secondEdgeLength = secondEdgeLength;
        this.thirdEdgeLength = thirdEdgeLength;
        quantity ++;
    }

    @Override
    double calculatePerimeter() {
        trianglePerimeter = firstEdgeLength + secondEdgeLength + thirdEdgeLength;
        return trianglePerimeter;
    }

    @Override
    double calculateArea() {
        double p = 0.5 * trianglePerimeter;
        triangleArea = Math.sqrt(p * (p - firstEdgeLength)*(p - secondEdgeLength)*(p - thirdEdgeLength));
        return triangleArea;
    }

    @Override
    public void print() {
        System.out.println("Dane trojkata: ");
        System.out.println("Dlugosci bokow: " + firstEdgeLength + "," + secondEdgeLength + "," + thirdEdgeLength);
        System.out.println("Obwod: " + calculatePerimeter());
        System.out.println("Pole: " + calculateArea());
        System.out.println(" ");
    }
}
