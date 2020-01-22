package models.adapters;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.GameEvent;

public class GameEventAdapter implements JsonSerializer<GameEvent>{
	@Override
    public JsonElement serialize(GameEvent gameEvent, Type type, JsonSerializationContext jsonSerializationContext) {        
    	JsonObject obj = new JsonObject();
    	DateFormat d = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        obj.addProperty("date", d.format(gameEvent.date));
        obj.addProperty("message", gameEvent.message);
        return obj;
    }
}
