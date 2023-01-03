package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorAdditionTest extends BaseTest {
    @DataProvider
    public static Object [][] sumCorrectData() {
        return new Object[][] {
                {1.0, 2.0, 3.0},
                {-1.0, 0.0, -1.0},
                {123.456, 987.654, 1111.110}
        };
    }

    @Test(dataProvider = "sumCorrectData")
    public void sumTwoDoublesValidResult(double a, double b, double res) {

        var actual = calculator.sum(a, b);

        assertThat(actual).isEqualTo(res, Offset.offset(0.001));
    }
}

