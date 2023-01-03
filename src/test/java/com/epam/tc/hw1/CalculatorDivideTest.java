package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorDivideTest extends BaseTest {
    @DataProvider
    public static Object [][] divideCorrectData() {
        return new Object[][] {
                {1, 2, 0},
                {-15, 5, -3},
                {123456, 987654, 0}
        };
    }

    @Test(dataProvider = "divideCorrectData")
    public void divTwoLongsReturnsValidResult(long a, long b, long result) {

        var actual = calculator.div(a, b);

        assertThat(actual).isEqualTo(result);
    }

    @DataProvider
    public static Object [][] divideByZeroLong() {
        return new Object[][] {
                {-15, 0}
        };
    }

    @Test(dataProvider = "divideByZeroLong")
    public void divTwoLongsThrowsException(long a, long b) {

        assertThatThrownBy(() -> calculator.div(a, b))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("Attempt to divide by zero");
    }

    @DataProvider
    public static Object [][] divideCorrectDataWithDouble() {
        return new Object[][] {
                {1.0, 2.0, 0.5},
                {-15.0, 1.5, -10.0},
                {123456, 987654, 0.12499924}
        };
    }

    @Test(dataProvider = "divideCorrectDataWithDouble")
    public void divTwoDoublesReturnsValidResult(double a, double b, double result) {

        var actual = calculator.div(a, b);

        assertThat(actual).isEqualTo(result, Offset.offset(0.001));
    }

    @DataProvider
    public static Object [][] divideByZeroDouble() {
        return new Object[][] {
                {1.11, 0.0}
        };
    }

    @Test(dataProvider = "divideByZeroDouble")
    public void divTwoDoublesThrowsException(double a, double b) {
        assertThatThrownBy(() -> calculator.div(a, b)).isInstanceOf(NumberFormatException.class);
    }
}