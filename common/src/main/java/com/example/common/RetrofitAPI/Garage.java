package com.example.common.RetrofitAPI;

import java.util.List;

// Garage class for save the data from json (API)
public class Garage {
    private List<String> Cars;
    private boolean open = true;
    private String address = "";
    private String name = "";

    public Garage() {}

    public List<String> getCars() {
        return Cars;
    }

    public void setCars(List<String> cars) {
        this.Cars = cars;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
