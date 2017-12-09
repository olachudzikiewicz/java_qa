package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test

  public void TestDistancePositive() {

    Point point1 = new Point(7,2);
    Point point2 = new Point(1,2 );

    Assert.assertEquals(point1.distance(point2),6.0);
  }

  @Test
  public void TestDistanceNegative() {

    Point point1 = new Point(1,2);
    Point point2 = new Point(1,2 );

    Assert.assertEquals(point1.distance(point2),7.0);
  }
}
