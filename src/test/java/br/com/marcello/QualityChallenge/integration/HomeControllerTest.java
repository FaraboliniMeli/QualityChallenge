package br.com.marcello.QualityChallenge.integration;

import br.com.marcello.QualityChallenge.dto.HomeDto;
import br.com.marcello.QualityChallenge.dto.RoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    private final Double DISTRICT_SQUARE_METER_PRICE = 40.0;
    private final Double BEDROOM_AREA = 15.0;
    private final Double BATHROOM_AREA = 4.0;
    private final Double HOME_AREA = this.BATHROOM_AREA + this.BEDROOM_AREA;

    static HomeDto homeDto;
    static RoomDto bathroom;
    static RoomDto bedroom;
    static List<RoomDto> roomList;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        bathroom = new RoomDto();
        bathroom.setName("Bathroom");
        bathroom.setRoomWidth(2.0);
        bathroom.setRoomLength(2.0);

        bedroom = new RoomDto();
        bedroom.setName("Bedroom");
        bedroom.setRoomLength(5.0);
        bedroom.setRoomWidth(3.0);

        roomList = new ArrayList<>();
        roomList.add(bathroom);
        roomList.add(bedroom);

        homeDto = new HomeDto();
        homeDto.setName("Home");
        homeDto.setDistrict("Vila Nova Conceição");
        homeDto.setRoomList(roomList);
    }

    @Test
    public void getRoomsAreaList() throws Exception {
        this.mockMvc.perform(post("/home/getAllRoomsArea")
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(roomList)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomsAreas").isMap())
                .andExpect(jsonPath("$.roomsAreas.Bathroom").value(this.BATHROOM_AREA))
                .andExpect(jsonPath("$.roomsAreas.Bedroom").value(this.BEDROOM_AREA));
    }

    @Test
    public void getHomeArea() throws Exception {
        this.mockMvc.perform(post("/home/getHomeArea")
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(homeDto)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.homeArea").value(this.HOME_AREA));
    }

    @Test
    public void getMaxRoom() throws Exception {
        this.mockMvc.perform(post("/home/getMaxRoom")
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(homeDto)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.maxRoom").isMap())
                .andExpect(jsonPath("$.maxRoom.name").value("Bedroom"))
                .andExpect(jsonPath("$.maxRoom.roomWidth").value(3.0))
                .andExpect(jsonPath("$.maxRoom.roomLength").value(5.0))
                .andExpect(jsonPath("$.maxRoomArea").value(this.BEDROOM_AREA));
    }

    @Test
    public void getHomePrice() throws Exception {
        this.mockMvc.perform(post("/home/getHomePrice")
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(homeDto)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.home").isMap())
                .andExpect(jsonPath("$.district").isMap())
                .andExpect(jsonPath("$.district.districtName").value(homeDto.getDistrict()))
                .andExpect(jsonPath("$.district.squareMeterPrice").value(this.DISTRICT_SQUARE_METER_PRICE))
                .andExpect(jsonPath("$.homePrice").value(this.HOME_AREA * this.DISTRICT_SQUARE_METER_PRICE));
    }

}
