package com.technikag.invent;

import javax.persistence.*;


@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private final DeviceType type;

    private String name;

    @ManyToOne
    private Location location;

    private String comment;

    public Device(DeviceType type, String name, Location location, String comment) {
        this.type = type;
        this.name = name;
        this.location = location;
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

}
