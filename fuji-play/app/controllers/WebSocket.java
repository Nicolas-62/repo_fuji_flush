package controllers;

import static play.libs.F.Matcher.ClassOf;
import static play.mvc.Http.WebSocketEvent.TextFrame;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import models.Game;
import models.GameRoom;
import models.GameTable;
import play.libs.F.Either;
import play.libs.F.EventStream;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http.WebSocketEvent;
import play.mvc.WebSocketController;

public class WebSocket extends Controller {

	public static class GameSocket extends WebSocketController {

		public static void game(String gameUuid, String email) {

			GameTable gameTable = GameTable.get();

			// Socket connected, join the chat room
			EventStream<JsonObject> roomTurnsStream = gameTable.join();

			// Loop while the socket is open
			while (inbound.isOpen()) {
				Either<WebSocketEvent, JsonObject> e = await(
						Promise.waitEither(inbound.nextEvent(), roomTurnsStream.nextEvent()));
                
				// Case: card play received on the socket
                for(String message: TextFrame.match(e._1)) {
                	int cardIndex = Integer.parseInt(message);
                	// if handId = -1 the player want to leave de game
                	if(cardIndex < 0) {
                		gameTable.leave(gameUuid, email);
                	}else {
	                	System.out.println("card play received on the socket");
	                	gameTable.playCard(gameUuid, email, cardIndex);
                	}
                }
                for(JsonObject game: ClassOf(JsonObject.class).match(e._2)) {
                	System.out.println("sending game in json to " + email);
                    outbound.sendJson(game);
                }
                
			}
		}
		public static void room(String email) {
			
			GameRoom gameRoom = GameRoom.get();
			// Socket connected, join the chat room
			EventStream<JsonArray> gameRoomStream = gameRoom.join();

			// Loop while the socket is open
			while (inbound.isOpen()) {
				Either<WebSocketEvent, JsonArray> e = await(
						Promise.waitEither(inbound.nextEvent(), gameRoomStream.nextEvent()));
                
				// Case: new game created received on the server
                for(String gameReceived : TextFrame.match(e._1)) {
                	Gson gson = new Gson();
                	Game game = gson.fromJson(gameReceived, Game.class);
                	// if it's a game to join
                	if(game.id != null) {
                		System.out.println("Websocket, game to join, id : "+ game.id);
                		gameRoom.joinGame(game, email);
                	}// else it's a new game to create
                	else {
                		System.out.println("Websocket, game to create, nbPlayerMissing : "+ game.nbPlayerMissing);
                		gameRoom.addGame(game);            		
                	}
                }
                // Case: new Event, a game was created by the server, want to send it
                for(JsonArray games : ClassOf(JsonArray.class).match(e._2)) {
                	System.out.println("sending games in json to " + email);
                    outbound.sendJson(games);
                }
                
			}			
		}
	}
}
