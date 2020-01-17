package controllers;

import static play.libs.F.Matcher.ClassOf;
import static play.mvc.Http.WebSocketEvent.TextFrame;

import com.google.gson.JsonObject;

import models.GameRoom;
import play.libs.F.Either;
import play.libs.F.EventStream;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http.WebSocketEvent;
import play.mvc.WebSocketController;

public class WebSocket extends Controller {

	public static class GameSocket extends WebSocketController {

		public static void join(String uuid, Long handId, String playerNickName) {

			GameRoom room = GameRoom.get();

			// Socket connected, join the chat room
			EventStream<JsonObject> roomTurnsStream = room.join(playerNickName);

			// Loop while the socket is open
			while (inbound.isOpen()) {
				System.out.println("socket is open");
				Either<WebSocketEvent, JsonObject> e = await(
						Promise.waitEither(inbound.nextEvent(), roomTurnsStream.nextEvent()));
                
				// Case: card play received on the socket
                for(String cardIndex: TextFrame.match(e._1)) {
                	System.out.println("card play received on the socket by " + playerNickName );
                	System.out.println("cardIndex : " + cardIndex);
                   room.playCard(handId, Integer.parseInt(cardIndex), uuid);
                }
                for(JsonObject hands: ClassOf(JsonObject.class).match(e._2)) {
                	System.out.println("sending hands in json");
                    outbound.sendJson(hands);
                }
                
			}
		}
	}
}
