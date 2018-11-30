package com.wcx.springboot.demo.midware.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.ArrayList;
import java.util.List;

@Entity("hotels")
@Indexes({
        @Index(fields = @Field(value = "stars", type = IndexType.ASC))
})
public class Hotel {
    @Id
    private ObjectId id;

    private String name;
    private int stars;

    @Embedded
    private Address address;

    List<Integer> roomNumbers = new ArrayList<Integer>();

    // ... optional getters and setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(List<Integer> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }
}
