package com.assignment.coding.furnitureapp.models;

import java.io.Serializable;

/**
 * Created by victo on 03/05/2018.
 */

public class Data implements Serializable {
    private String location;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
