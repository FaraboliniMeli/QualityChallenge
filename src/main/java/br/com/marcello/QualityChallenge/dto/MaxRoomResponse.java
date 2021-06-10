package br.com.marcello.QualityChallenge.dto;

public class MaxRoomResponse {

    private RoomDto maxRoom;
    private Double maxRoomArea;

    public RoomDto getMaxRoom() {
        return maxRoom;
    }

    public void setMaxRoom(RoomDto maxRoom) {
        this.maxRoom = maxRoom;
    }

    public Double getMaxRoomArea() {
        return maxRoomArea;
    }

    public void setMaxRoomArea(Double maxRoomArea) {
        this.maxRoomArea = maxRoomArea;
    }
}
