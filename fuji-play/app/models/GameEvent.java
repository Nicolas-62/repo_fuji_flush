package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class GameEvent extends UUIDModel {

    @OneToOne
    public Game game;
    public Date date;
    public String message;
}
