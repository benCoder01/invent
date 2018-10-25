package com.technikag.invent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateLocationDTO {
    String name;

    public String getName() {
        return name;
    }


}
