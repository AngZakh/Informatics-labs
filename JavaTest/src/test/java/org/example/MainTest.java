package org.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.example.Main.sum;
import static org.example.Main.minus;
import static org.example.Main.umnozhenie;
import static org.example.Main.delenie;
import static org.example.Main.sqrt;

public class MainTest {
    @Test(groups = {"math"})
    public void testSum() {
        final int NUMBER1 = 5;
        final int NUMBER2 = 7;
        final int expectedResult = 12;
        int actualResult;
        actualResult = sum(NUMBER1, NUMBER2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"math"})
    public void testMinus() {
        final int NUMBER1 = 15;
        final int NUMBER2 = 7;
        final int expectedResult = 8;
        int actualResult;
        actualResult = minus(NUMBER1, NUMBER2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"math"})
    public void testUmnozhenie() {
        final int NUMBER1 = 6;
        final int NUMBER2 = 8;
        final int expectedResult = 48;
        int actualResult;
        actualResult = umnozhenie(NUMBER1, NUMBER2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"math"})
    public void testDelenie() {
        final int NUMBER1 = 18;
        final int NUMBER2 = 3;
        final double expectedResult = 6;
        double actualResult;
        actualResult = delenie(NUMBER1, NUMBER2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = {IndexOutOfBoundsException.class}, groups = {"exceptions"})
    public void testDelenieExc() {
        final int NUMBER1 = 7;
        final int NUMBER2 = 0;
        delenie(NUMBER1, NUMBER2);
    }

    @Test(groups = {"math"})
    public void testSqrt() {
        final int NUMBER = 64;
        final double expectedResult = 8;
        double actualResult;
        actualResult = sqrt(NUMBER);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = {IndexOutOfBoundsException.class}, groups = {"exceptions"})
    public void testSqrtExc() {
        final int NUMBER = -9;
        sqrt(NUMBER);
    }
    @DataProvider(name = "UmnozhenieData")
    public Object[][] UmnozhenieData() {
        return new Object[][] {
                {6, 2, 13},
                {7, 5, 35},
                {21, 3, 63}
        };
    }
    @Test(dataProvider = "UmnozhenieData", groups = {"math", "data"})
    public void testUmnozhenie2(int NUMBER1, int NUMBER2, int expectedResult) {
        int actualResult;
        actualResult = umnozhenie(NUMBER1, NUMBER2);
        Assert.assertEquals(actualResult, expectedResult);
    }
}

