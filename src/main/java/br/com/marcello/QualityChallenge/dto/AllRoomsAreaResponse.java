package br.com.marcello.QualityChallenge.dto;

import java.util.Map;

public class AllRoomsAreaResponse {

    private Map<String, Double> roomsAreas;

    public Map<String, Double> getRoomsAreas() {
        return roomsAreas;
    }

    public void setRoomsAreas(Map<String, Double> roomsAreas) {
        this.roomsAreas = roomsAreas;
    }
}
