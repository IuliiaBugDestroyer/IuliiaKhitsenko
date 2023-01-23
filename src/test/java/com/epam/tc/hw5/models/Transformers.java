package com.epam.tc.hw5.models;

import io.cucumber.java.DataTableType;
import java.util.Map;

public class Transformers {
    @DataTableType
    public static User userEntryTransformer(Map<String, String> row) {
        return new User(
            Integer.parseInt(row.get("Number")),
            row.get("User"),
            row.get("Description")
        );
    }
}
