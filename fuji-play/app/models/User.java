package models;

import javax.persistence.Entity;

@Entity
public class User extends UUIDModel {

    public String email;

    public String password;

    public String nickName;

    public int score;

    public long ranka;

}
