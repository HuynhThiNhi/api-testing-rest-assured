package controller;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class POSTMethod {
    public static void main(String[] args) {

        RequestSpecification request = given();
        String baseUri = "https://jsonplaceholder.typicode.com";


//        JSONObject requestParams = new JSONObject();
//        requestParams.put("title", "foo");
//        requestParams.put("body", "bar");
//        requestParams.put("userId", 1);
        Post body = new Post(1, "foo", "bar");
        Gson bodyJson = new Gson();

        Response res = request.baseUri(baseUri).
                header("Content-Type", "application/json; charset=UTF-8")
                .body(bodyJson.toJson(body)) // using gson
//                .body(body.toString())  // using jackson
//                .body(requestParams.toString()) // using json
                .basePath("/posts").post();

        System.out.printf(res.getStatusLine());
       // System.out.print(res.statusCode());

        // verification
        res.then().statusCode(equalTo(201));
        res.then().statusLine(containsStringIgnoringCase("201 Created"));
        res.then().body("userId", equalTo(1));

    }

    public static Response makePostRequest(String url, String body) {
        RequestSpecification request = given();
        return request.baseUri(url).
                header("Content-Type", "application/json; charset=UTF-8")
                .body(body)
                .post();
    }


}
