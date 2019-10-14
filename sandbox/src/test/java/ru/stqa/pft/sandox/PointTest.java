package ru.stqa.pft.sandox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {
    @Test
    public void testDistance(){
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 2);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }
}
