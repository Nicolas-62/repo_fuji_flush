package models.adaptaters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Hand;
import models.User;
public class HandAdaptater implements JsonSerializer<Hand> {

	    @Override
	    public JsonElement serialize(Hand hand, Type type, JsonSerializationContext jsonSerializationContext) {
	        JsonObject obj = new JsonObject();
	        obj.addProperty("id", hand.id);
	        obj.addProperty("cardP", hand.cardP.value);
	        obj.add("cards", jsonSerializationContext.serialize(hand.cards));
	        return obj;
	    }
}
