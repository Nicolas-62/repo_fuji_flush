package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Card extends IdModel {

    public Integer value;
    

}
