package models;

import javax.persistence.Entity;

@Entity
public class User extends IdModel {

    public String email;

    public String password;

    public String firstName;

    public String lastName;

}
