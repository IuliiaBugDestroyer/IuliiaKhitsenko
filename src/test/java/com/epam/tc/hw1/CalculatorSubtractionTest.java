package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorSubtractionTest extends BaseTest {
    @DataProvider
    public static Object [][] subtractCorrectData() {
        return new Object[][] {
                {1, 2, -1},
                {-1, -1, 0},
                {123456, 987654, -864198}
        };
    }

    @Test(dataProvider = "subtractCorrectData")
    public void subTwoLongsReturnsValidResult(long a, long b, long result) {

        var actual = calculator.sub(a, b);

        assertThat(actual).isEqualTo(result);
    }
}
