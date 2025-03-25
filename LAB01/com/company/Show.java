package com.company;
import java.util.Scanner;

public class Show {
    Scanner scanner = new Scanner(System.in);
    public void userInterface(){
        boolean switcher = true;
        while(switcher) {
            System.out.println("Wybierz jedna z podanych opcji: ");
            System.out.println("1: Dodanie figury \n" + "2: Licznik stworzonych figur \n" + "3. Wyjscie" );
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Wybierz jaka figure chcesz dodac. ");
                    selectFigure();
                    break;
                case 2:
                    System.out.printf("Liczba stworzonych figur: %d \n", Figure.quantity );
                    break;
                case 3:
                    System.out.print("Zegnam Pania");
                    switcher = false;
                    break;
                default:
                    System.out.print("Nieprawidlowa wartosc");

            }
        }

    }
    void selectFigure(){
        System.out.println("Dostępne figury: ");
        System.out.println("a) Kwadrat \n" + "b) Trojkat \n" + "c) Kolo \n" + "d) Graniastoslup\n" + "e) Powrot");
        String letter = scanner.next();
            switch(letter){
                case "a": //kwadrat
                    Square square = addSquare();
                    if(square != null)
                        square.print();
                    break;

                case "b": //trojkat
                   Triangle triangle = addTriangle();
                   if(triangle != null)
                       triangle.print();
                    break;

                case "c": //kolo
                    Circle circle = addCircle();
                    if(circle != null)
                        circle.print();
                    break;

                case "d": //graniastoslup
                    System.out.print("Podaj wysokosc: ");
                    double height = scanner.nextDouble();
                        System.out.println("Wybierz podstawe: ");
                        System.out.println("1: Kwadrat\n" + "2: Trojkat\n" + "3. Kolo");
                        int option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                addPrism(height, addSquare());
                                break;
                            case 2:
                                addPrism(height, addTriangle());
                                break;
                            case 3:
                                addPrism(height, addCircle());
                                break;
                            default:
                                throw new IllegalStateException("Niespodziewana wartosc: " + option);
                        }
                    break;

                case "e":
                    break;
                default:
                    System.out.print("Nieprawidlowa wartosc");
            }

    }
    Square addSquare(){
        System.out.print("Podaj dlugosc krawedzi: ");
        double squareEdge = scanner.nextDouble();

        try { //wewnatrz try sprawdza generowanie wyajątkow (podobnie jak python)
            Square square1 = new Square(squareEdge);
            return square1;
        } catch (IllegalArgumentException exeption){ //jeśli wyłapie dany wyjątek to wykonuje dana instrukcję
            System.out.println("Wyjatek: " + exeption.getMessage()); //zwraca wiadomosc wyjątku
            return null;
        }
    }
    Triangle addTriangle(){
        System.out.print("Podaj dlugosc: \npierwszego boku: ");
        double firstEdge = scanner.nextDouble();
        System.out.print("drugiego boku: ");
        double secondEdge = scanner.nextDouble();
        System.out.print("trzeciego boku: ");
        double thirdEdge = scanner.nextDouble();

        try{
            Triangle triangle1 = new Triangle(firstEdge,secondEdge,thirdEdge);
            return triangle1;
        } catch (IllegalArgumentException exeption){
            System.out.println("Wyjatek: " + exeption.getMessage());
            return null;
        }
    }
    Circle addCircle(){
        System.out.print("Podaj dlugosc promienia: ");
        double radius = scanner.nextDouble();

        try{
            Circle circle1 = new Circle(radius);
            return circle1;
        } catch (IllegalArgumentException exeption){
            System.out.println("Wyjatek: " + exeption.getMessage());
            return null;
        }
    }
    void addPrism(double height, Figure shape){
        try { //wewnatrz try sprawdza generowanie wyajątkow (podobnie jak python)
            Prism prism1 = new Prism(height, shape);
            prism1.print();
        } catch (IllegalArgumentException exeption){ //jeśli wyłapie dany wyjątek to wykonuje dana instrukcję
            System.out.println("Wyjatek: " + exeption.getMessage()); //zwraca wiadomosc wyjątku
        }
    }

}
