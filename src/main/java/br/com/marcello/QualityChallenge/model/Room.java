package br.com.marcello.QualityChallenge.model;

public class Room {

    private String name;
    private Double roomWidth;
    private Double roomlength;

    public Room(String name, Double roomWidth, Double roomlength) {
        this.name = name;
        this.roomWidth = roomWidth;
        this.roomlength = roomlength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(Double roomWidth) {
        this.roomWidth = roomWidth;
    }

    public Double getRoomlength() {
        return roomlength;
    }

    public void setRoomlength(Double roomlength) {
        this.roomlength = roomlength;
    }
}
