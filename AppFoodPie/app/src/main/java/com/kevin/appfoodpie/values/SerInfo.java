package com.kevin.appfoodpie.values;

import java.io.Serializable;

/**
 * Created by dllo on 16/12/1.
 */

public class SerInfo implements Serializable{

    private int id;
    private String name;

    public SerInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SerInfo() {
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
}
