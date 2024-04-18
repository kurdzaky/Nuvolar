package com.nuvolar.dataproviders;

import org.testng.annotations.DataProvider;

import static com.nuvolar.constants.Global.*;

public class DataProviders {

    private DataProviders() {
        throw new IllegalStateException("Utility class");
    }

    @DataProvider(name = "petStatus")
    public static Object[][] petStatus() {

        return new Object[][] {{STATUS_AVAILABLE}, {STATUS_SOLD}, {STATUS_PENDING}};
    }
}
