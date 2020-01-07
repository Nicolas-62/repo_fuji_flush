package models;

import play.libs.Codec;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UUIDModel extends IdModel {

    @Column(length = 40)
    public String uuid;

    public UUIDModel(){
        this.uuid= Codec.UUID( );
    }
}
