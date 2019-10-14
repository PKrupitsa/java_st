package ru.stqa.pft.sandox;

public class MyFirstProgram {

    public static void main(String[] args) { //главный метод класса (entry point)
        Point p1 = new Point(0,1); // локальная переменная p1 в методе main
        Point p2 = new Point(0,2); // локальная переменная p2 в методе main

        System.out.println("Расстояние между точками - " + p1.distance(p2));
    }
    }

