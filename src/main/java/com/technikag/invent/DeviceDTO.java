package com.technikag.invent;

import java.util.Optional;

public class DeviceDTO {

    private int id;
    private String name;
    private LocationDTO location;
    private String comment;
    private DeviceTypeDTO type;

    public DeviceTypeDTO getType() {
        return type;
    }

    public void setType(DeviceTypeDTO type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DeviceDTO(int id, String name, LocationDTO location, String comment, DeviceTypeDTO type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.comment = comment;
        this.type = type;
    }

}
