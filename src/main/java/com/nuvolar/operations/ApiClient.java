package com.nuvolar.operations;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nuvolar.constants.Global.EXPECTED_RESPONSE_TIME;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ApiClient {
    public ApiClient() {
        super();
    }

    public ValidatableResponse doGetRequest(
                                            Map<String, String> pathParameterKeyValue,
                                            Map<String, List<String>> queryParamKeyValues, String endpoint,
                                            RequestSpecification requestSpecification) {

        return given().spec(requestSpecification)
                .queryParams(queryParamKeyValues != null ? queryParamKeyValues
                        : Collections.emptyMap())
                .pathParams(pathParameterKeyValue != null ? pathParameterKeyValue
                        : Collections.emptyMap())
                .when()
                .get(endpoint)
                .then()
                .time(lessThanOrEqualTo(EXPECTED_RESPONSE_TIME));
    }
}
