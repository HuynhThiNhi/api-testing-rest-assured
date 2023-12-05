package builder;

import com.google.gson.Gson;

public class JsonBuilder {
    public static <T> String toJson(T object) {
        return new Gson().toJson(object);
    }
}
