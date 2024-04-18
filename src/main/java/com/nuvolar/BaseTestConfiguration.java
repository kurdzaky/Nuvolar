package com.nuvolar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static com.nuvolar.constants.Endpoints.URL_BASE_PET_STORE;
import static com.nuvolar.constants.Global.EXPECTED_RESPONSE_TIME;
import static com.nuvolar.constants.Global.VALUE_APPLICATION_JSON;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public abstract class BaseTestConfiguration extends QATestListener {

        protected Logger logger = LoggerFactory.getLogger(this.getClass()
                .getName());
        protected static RequestSpecification requestSpec;
        protected static RequestSpecification requestSpecWithoutContentType;
        protected static RequestSpecification requestSpecStream;
        protected static RequestSpecification requestSpecStar;
        protected static ResponseSpecification responseSpec;


        @BeforeMethod(alwaysRun = true)
        public void setupBeforeTest(Method method) {
            createRequestSpecification();
            createResponseSpecification();
        }

        protected void createRequestSpecification() {
            RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(URL_BASE_PET_STORE)
                    .setContentType(VALUE_APPLICATION_JSON)
                    .setAccept(VALUE_APPLICATION_JSON);
            logger.info("Building Request Specification:\n{}", requestSpecBuilder.build().toString());
            setRequestSpec(requestSpecBuilder.build());
        }

        private static void setRequestSpec(RequestSpecification requestSpecification) {
            requestSpec = requestSpecification;
        }

        public static void createResponseSpecification() {
            responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .expectResponseTime(lessThanOrEqualTo(EXPECTED_RESPONSE_TIME))
                    .build();
        }
}
