package models.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Game;

public class GameForRoomAdapter implements JsonSerializer<Game> {

    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.add("author", jsonSerializationContext.serialize(game.author));
        obj.addProperty("id", game.id);
        obj.addProperty("nbPlayerMissing", game.nbPlayerMissing);
        obj.addProperty("isFinished", game.isFinished);
        return obj;
    }
}
