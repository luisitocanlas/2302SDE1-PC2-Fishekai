package com.fishekai.models;

public class Flask extends Item {

    private static int charges = 0;

    public Flask(String name) {
        super(name);
    }

    public Flask(String name, String type, String description) {
        super(name, type, description);
    }

    public Flask(String name, String type, String description, String location, int modifier) {
        super(name, type, description, location, modifier);
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        Flask.charges = charges;
    }


}