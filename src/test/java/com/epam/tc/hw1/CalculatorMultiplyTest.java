package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorMultiplyTest extends BaseTest {
    @DataProvider
    public static Object[][] multiplyCorrectData() {
        return new Object[][]{
                {1, 2, 2},
                {-1, 0, 0},
                {-1234, 7654, -9445036}
        };
    }

    @Test(dataProvider = "multiplyCorrectData")
    public void multTwoLongsReturnsValidResult(long a, long b, long result) {

        var actual = calculator.mult(a, b);

        assertThat(actual).isEqualTo(result);
    }
}
