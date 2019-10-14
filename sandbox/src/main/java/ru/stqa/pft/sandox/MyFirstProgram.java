package ru.stqa.pft.sandox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(0,1);
        Point p2 = new Point(0,2);

        System.out.println("Расстояние между точками - " + p1.distance(p2));
    }
    }

