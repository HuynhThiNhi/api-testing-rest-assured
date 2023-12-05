package common.utils;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

import java.util.function.Function;

import static io.restassured.RestAssured.given;

public interface RequestCapability {
    Header defaultHeader = new Header("Content-Type", "application/json; charset=UTF-8");
    Header acceptJsonHeader = new Header("Accept", "application/json");
    RequestSpecification request = given();

    static Header getAuthenticatedHeader(String encodedCredential) {
        if (encodedCredential.isEmpty())  {
            throw new IllegalArgumentException("encodedCredential is required");
        }
        return new Header("Authorization", "Basic " + encodedCredential);
    }
    Function<String, Header> getAuthenticatedHeader = encodedCredential -> {
        if (encodedCredential.isEmpty())  {
            throw new IllegalArgumentException("encodedCredential is required");
        }
        return new Header("Authorization", "Basic " + encodedCredential);
    };
}
