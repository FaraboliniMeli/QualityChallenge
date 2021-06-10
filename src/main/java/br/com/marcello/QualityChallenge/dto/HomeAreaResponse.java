package br.com.marcello.QualityChallenge.dto;

import javax.validation.Valid;
import java.util.List;

public class HomeAreaResponse {

    private String name;
    private DistrictDto district;
    private List<RoomDto> rooms;
    private Double homeArea;

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }

    public Double getHomeArea() {
        return homeArea;
    }

    public void setHomeArea(Double homeArea) {
        this.homeArea = homeArea;
    }
}
