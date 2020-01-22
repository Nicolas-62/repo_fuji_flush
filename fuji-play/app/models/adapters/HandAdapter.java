package models.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Hand;

public class HandAdapter implements JsonSerializer<Hand> {

    @Override
    public JsonElement serialize(Hand hand, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("uuid", hand.uuid);
        obj.add("player", jsonSerializationContext.serialize(hand.player));
        obj.add("cards", jsonSerializationContext.serialize(hand.cards));
        obj.add("cardP", jsonSerializationContext.serialize(hand.cardP));
        obj.addProperty("hasLeft", hand.hasLeft);
        obj.addProperty("hasWon", hand.hasWon);
        obj.addProperty("hasLeaveWon", hand.hasLeaveWon);
        return obj;
    }
}
