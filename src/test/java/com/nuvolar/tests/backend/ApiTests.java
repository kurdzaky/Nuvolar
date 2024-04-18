package com.nuvolar.tests.backend;

import com.nuvolar.BaseTestConfiguration;
import com.nuvolar.dataproviders.DataProviders;
import com.nuvolar.model.Pets;
import com.nuvolar.operations.PetApi;
import com.nuvolar.tests.Scenario;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.nuvolar.constants.PathParameters.PATH_PARAM_STATUS;
import static java.util.Collections.singletonList;

public class ApiTests extends BaseTestConfiguration {
    PetApi petApi = new PetApi();

    @Scenario(description = "Test pet/findByStatus endpoint and make sure it returns data according to the scheme and return status code 200",
            steps = {"Execute get request in pet/findByStatus endpoint - status code 200, and response is according to the scheme",})
    @Test(groups = {"backend"}, dataProvider = "petStatus", dataProviderClass = DataProviders.class)
    public void testExecuteGetRequestPetFindByStatusWithAllStatuses_makeSureEndpointReturnsDataAccordingScheme(String status) {
        HashMap<String, List<String>> queryParamMap = new HashMap<>();
        queryParamMap.put(PATH_PARAM_STATUS, singletonList(status));

        Pets[] getPet = petApi.getPetByStatus(queryParamMap, requestSpec)
                .statusCode(200)
                .extract()
                .as(Pets[].class);
    }
    }