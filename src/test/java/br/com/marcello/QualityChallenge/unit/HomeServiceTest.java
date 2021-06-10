package br.com.marcello.QualityChallenge.unit;

import br.com.marcello.QualityChallenge.dto.HomeDto;
import br.com.marcello.QualityChallenge.dto.RoomDto;
import br.com.marcello.QualityChallenge.service.HomeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    static HomeDto homeDto;
    static RoomDto bathroom;
    static RoomDto bedroom;

    @InjectMocks
    @Autowired
    HomeServiceImpl homeService;

    @BeforeAll
    public static void setup() {
        bedroom = new RoomDto();
        bedroom.setName("Bedroom");
        bedroom.setRoomWidth(3.);
        bedroom.setRoomLength(5.);

        bathroom = new RoomDto();
        bathroom.setName("Bathroom");
        bathroom.setRoomWidth(2.);
        bathroom.setRoomLength(2.);

        List<RoomDto> roomsDto = new ArrayList<>();
        roomsDto.add(bedroom);
        roomsDto.add(bathroom);

        homeDto = new HomeDto();
        homeDto.setName("Casa");
        homeDto.setDistrict("Vila Regente Feijó");
        homeDto.setRoomList(roomsDto);
    }

    @Test
    public void roomAreaWellCalculated() {
        Double bedroomArea = homeService.getRoomArea(bedroom);
        Assertions.assertEquals(bedroomArea.doubleValue(), 15., "Bedroom area should be equals 15.");
    }

    @Test
    public void homeAreaWellCalculated() {
        Double homeArea = homeService.getHomeArea(homeDto);
        Assertions.assertEquals(homeArea.doubleValue(), 19, "Home area should be equals 19.");
    }

    @Test
    public void getMaxRoomWellDone() {
        String maxRoomName = bedroom.getName();
        String maxRoomNameByService = this.homeService.getMaxRoom(homeDto).getName();
        Assertions.assertEquals(maxRoomName, maxRoomNameByService, "Max room should be bedroom.");
    }

    @Test
    public void homePriceWellCalculated() {
        Double homeprice = homeService.getHomePrice(homeDto);
        Assertions.assertEquals(199.5, homeprice, "Home price should be 199.5");
    }

}
