package controller;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Todo;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SimpleTest {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";
        // request scope
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.basePath("/todos");

        // response scope
        // final String FIRST_TODO = "/1";
        // Response res = request.get(FIRST_TODO);
       // res.prettyPrint();

        // Read JSON Response Body using Rest Assured
        //res.then().body("userId", equalTo(1));

        //to Extract a Node text from Response using JsonPath?
//        res.jsonPath().getJsonObject()

        // extract response to defined object
        // Todo todo = res.getBody().as(Todo.class);
        //  System.out.println(todo.toString());
        Response res = request.get();
        List<Todo> todos = Arrays.asList(res.getBody().as(Todo[].class));
        System.out.printf(todos.toString());


    }
    public void GetMethod() {

    }

}

