package br.com.marcello.QualityChallenge.dto;

import javax.validation.constraints.*;

public class RoomDto {

    @NotBlank(message = "Room name field is obligatory.")
    @Size(max = 30, message = "Only 30 characters allowed to room name field.")
    @Pattern(regexp = "^[A-Z][A-za-z\\s]*$", message = "First characters must be Upper Case, only letters allowed for room's name")
    private String name;

    @NotNull(message = "Room's width cannot be null.")
    @DecimalMin(value = "0.1", message = "Room's width must be greater than 0")
    @DecimalMax(value = "25.0", message = "Room's width must be lower than 25")
    private Double roomWidth;

    @NotNull(message = "Room's length cannot be null.")
    @DecimalMin(value = "0.1", message = "Room's length must be greater than 0")
    @DecimalMax(value = "33.0", message = "Room's length must be lower than 33")
    private Double roomLength;

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

    public Double getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(Double roomLength) {
        this.roomLength = roomLength;
    }
}
