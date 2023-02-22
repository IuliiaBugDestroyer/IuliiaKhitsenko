package com.epam.tc.hw7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[][] metalsPageData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        var testDataValues = mapper.readValue(new File("src/test/resources/JDI_ex8_metalsColorsDataSet.json"),
            new TypeReference<Map<String, MetalsPageTestData>>() {
            });

        return testDataValues.values().stream().map(x -> new Object[] {x}).collect(Collectors.toList())
                             .toArray(new Object[testDataValues.values().size()][1]);
    }
}
