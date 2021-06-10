package br.com.marcello.QualityChallenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class HomeDto {

    @NotBlank(message = "Room name field is obligatory.")
    @Size(max = 30, message = "Only 30 characters allowed to room name field.")
    @Pattern(regexp = "^[A-Z][A-za-z\\s]*$", message = "First characters must be Upper Case, only letters allowed for home's name")
    private String name;

    @NotBlank(message = "Home district field is obligatory.")
    @Size(max = 45, message = "Only 45 characters allowed to district name field.")
    private String district;

    @Valid
    private List<RoomDto> roomList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<RoomDto> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomDto> roomList) {
        this.roomList = roomList;
    }
}
