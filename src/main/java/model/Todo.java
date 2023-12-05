package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Todo {
    public int userId;
    public int id;
    public String title;
    public Boolean completed;

    public static void main(String[] args) {
        Todo todo = new Todo();
        todo.id = 1;
        System.out.println(todo.toString());
    }

//    public String toString(Todo obj) {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = "";
//        try {
//            json = ow.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return json;
//    }

    @Override
    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}

