package models.adapters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Game;
import models.GameEvent;
import services.GameEventService;

public class GameRoomAdapter implements JsonSerializer<Game> {

    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
       
    	JsonObject obj = new JsonObject();
        obj.addProperty("id", game.id);
        obj.addProperty("uuid", game.uuid);
        obj.add("author", jsonSerializationContext.serialize(game.author));
        obj.addProperty("nbPlayerMissing", game.nbPlayerMissing);
        obj.add("hands", jsonSerializationContext.serialize(game.hands));
        obj.add("currentPlayer", jsonSerializationContext.serialize(game.currentPlayer));
        obj.add("winners", jsonSerializationContext.serialize(game.winners));
        obj.addProperty("isFinished", game.isFinished);
        return obj;
    }

}
