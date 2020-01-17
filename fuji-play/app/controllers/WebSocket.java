package controllers;

import static play.libs.F.Matcher.ClassOf;
import static play.mvc.Http.WebSocketEvent.TextFrame;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.Game;
import models.GameRoom;
import models.GameTable;
import models.User;
import play.libs.F.Either;
import play.libs.F.EventStream;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http.WebSocketEvent;
import services.GameService;
import services.UserService;
import play.mvc.WebSocketController;

public class WebSocket extends Controller {

	public static class GameSocket extends WebSocketController {

		public static void joinGame(String uuid, Long handId, String playerNickName) {

			GameTable gameTable = GameTable.get();

			// Socket connected, join the chat room
			EventStream<JsonObject> roomTurnsStream = gameTable.join(playerNickName);

			// Loop while the socket is open
			while (inbound.isOpen()) {
				System.out.println("socket is open");
				Either<WebSocketEvent, JsonObject> e = await(
						Promise.waitEither(inbound.nextEvent(), roomTurnsStream.nextEvent()));
                
				// Case: card play received on the socket
                for(String cardIndex: TextFrame.match(e._1)) {
                	System.out.println("card play received on the socket by " + playerNickName );
                	System.out.println("cardIndex : " + cardIndex);
                	gameTable.playCard(handId, Integer.parseInt(cardIndex), uuid);
                }
                for(JsonObject hands: ClassOf(JsonObject.class).match(e._2)) {
                	System.out.println("sending hands in json");
                    outbound.sendJson(hands);
                }
                
			}
		}
		public static void gameRoom() {
			
			GameRoom gameRoom = GameRoom.get();
			// Socket connected, join the chat room
			EventStream<JsonObject> gameRoomStream = gameRoom.join();

			// Loop while the socket is open
			while (inbound.isOpen()) {
				System.out.println("socket is open");
				Either<WebSocketEvent, JsonObject> e = await(
						Promise.waitEither(inbound.nextEvent(), gameRoomStream.nextEvent()));
                
				// Case: new game created received on the server
                for(String gameReceived : TextFrame.match(e._1)) {
                	Gson gson = new Gson();
                	Game game = gson.fromJson(gameReceived, Game.class);
                	System.out.println("game received in Websocket, nbPlayerMissing : "+ game.nbPlayerMissing);
            		gameRoom.addGame(game);
                }
                // Case: new Event, a game was created by the server, want to send it
                for(JsonObject game : ClassOf(JsonObject.class).match(e._2)) {
                	System.out.println("sending game in json");
                    outbound.sendJson(game);
                }
                
			}			
		}
	}
}
