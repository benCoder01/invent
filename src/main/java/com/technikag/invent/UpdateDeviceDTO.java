package com.technikag.invent;

import java.util.Optional;

public class UpdateDeviceDTO {
    private Optional<String> name;
    private Optional<Integer> location;
    private Optional<String> comment;

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<Integer> getLocation() {
        return location;
    }

    public void setLocation(Optional<Integer> location) {
        this.location = location;
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(Optional<String> comment) {
        this.comment = comment;
    }
}
