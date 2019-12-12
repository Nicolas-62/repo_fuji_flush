package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Refresh extends Controller {

    public static void index(String user) {
        PlayRoom.get().join(user);
        room(user);
    }
    
    public static void room(String user) {
        List events = PlayRoom.get().archive();
        render(user, events);
    }
    
    public static void play(User user) {
        PlayRoom.get().play(user, message);
        room(user);
    }
    
    public static void leave(String user) {
        PlayRoom.get().leave(user);
        Application.index();
    }
    
}

