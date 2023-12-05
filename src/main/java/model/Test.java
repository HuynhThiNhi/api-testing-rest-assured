package model;

import io.restassured.http.Header;

import java.util.function.Function;
import java.util.stream.Stream;


public interface Test {
    Function<String, Header> getAuthenticatedHeader2 = encodedCredential -> {
        if (encodedCredential.isEmpty())  {
            throw new IllegalArgumentException("encodedCredential is required");
        }
        return new Header("Authorization", "Basic " + encodedCredential);
    };

    public static void main(String[] args) {
        int[] fibs = {0, 1};
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int result = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return result;
        });
    }

}
