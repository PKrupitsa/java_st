package ru.stqa.pft.sandox;

public class MyFirstProgram {

    public static void main(String[] args) {

            PointDist p1 = new PointDist(10, 10);

            PointDist p2 = new PointDist(20, 20);

            System.out.println("Расстояние между точками = " + p1.distance(0, 1));

        }
    }

