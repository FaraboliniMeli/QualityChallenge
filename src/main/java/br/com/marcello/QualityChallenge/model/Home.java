package br.com.marcello.QualityChallenge.model;

import java.util.List;

public class Home {

    private String name;
    private String homeDistrict;
    private List<Room> rooms;

    public Home(String name, String homeDistrict, List<Room> rooms) {
        this.name = name;
        this.homeDistrict = homeDistrict;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeDistrict() {
        return homeDistrict;
    }

    public void setHomeDistrict(String homeDistrict) {
        this.homeDistrict = homeDistrict;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
