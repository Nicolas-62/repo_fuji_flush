package models.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.User;

public class PlayerAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("email", user.email);
        obj.addProperty("nickName", user.nickName);
        obj.addProperty("score", user.score);
        obj.addProperty("ranka", user.ranka);
        return obj;
    }
}
