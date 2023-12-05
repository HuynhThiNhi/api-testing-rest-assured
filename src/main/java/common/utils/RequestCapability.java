package common.utils;

import io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapability {
    Header defaultHeader = new Header("Content-Type", "application/json; charset=UTF-8");
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
