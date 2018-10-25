package com.technikag.invent;

import java.util.List;
import java.util.Optional;

public class LocationDTO {

    private int id;
    private String name;
    private Optional<List<DeviceDTO>> packinglist;

    public LocationDTO(int id, String name, Optional<List<DeviceDTO>> packinglist) {
        this.id = id;
        this.name = name;
        this.packinglist = packinglist;
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

    public Optional<List<DeviceDTO>> getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(Optional<List<DeviceDTO>> packinglist) {
        this.packinglist = packinglist;
    }
}
