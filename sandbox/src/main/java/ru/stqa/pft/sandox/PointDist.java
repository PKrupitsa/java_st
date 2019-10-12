package ru.stqa.pft.sandox;

public class PointDist {

    public int x;
    public int y;

    public PointDist(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distance(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
