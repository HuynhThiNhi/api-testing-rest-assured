package controller;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Post;

import static io.restassured.RestAssured.given;

public class PutMethod {
    private static String updatedPostUrl = "https://jsonplaceholder.typicode.com/";
    public static void main(String[] args) {
        Post newPost = new Post();
        newPost.setBody("nhi");
        newPost.setTitle("test");
        newPost.setUserId(1);

       Response res = PutMethod.updatePost(1, newPost);
       //res.then().body()
        System.out.print(res.getStatusCode());
    }
    public static Response updatePost(int postId, Post newPost) {
        RequestSpecification request = given();
        Gson jsonBody = new Gson();
        String body = jsonBody.toJson(newPost);

        return request.baseUri(updatedPostUrl)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(body)
                .put("posts/".concat(String.valueOf(postId)));
    }

 }
