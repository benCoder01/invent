package com.technikag.invent;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @OneToMany
    private List<Device> packinglist;

    public Location() {}

    public Location(String name, Optional<List<Device>> packinglist) {
        this.name = name;
        this.setPackinglist(packinglist);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<List<Device>> getPackinglist() {
        return Optional.ofNullable(packinglist);
    }

    public void setPackinglist(Optional<List<Device>> packinglist) {
        packinglist.ifPresentOrElse(
                p -> this.packinglist = p,
                () -> this.packinglist = null
        );
    }

}
