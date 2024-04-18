package com.nuvolar.operations;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static com.nuvolar.constants.Endpoints.URL_FILTER_PET_BY_STATUS;

public class PetApi {

    ApiClient apiClient = new ApiClient();
    public ValidatableResponse getPetByStatus(Map<String, List<String>> queryParamMap, RequestSpecification requestSpecification) {

        return apiClient.doGetRequest(null, queryParamMap, URL_FILTER_PET_BY_STATUS, requestSpecification);
    }
}
