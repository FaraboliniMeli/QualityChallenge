package br.com.marcello.QualityChallenge.controller;

import br.com.marcello.QualityChallenge.dto.*;
import br.com.marcello.QualityChallenge.model.Home;
import br.com.marcello.QualityChallenge.repository.DistrictRepository;
import br.com.marcello.QualityChallenge.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private DistrictRepository districtRepository;

    @PostMapping("/getAllRoomsArea")
    public ResponseEntity<?> getAllRoomsArea(@RequestBody List<@Valid RoomDto> roomsList) {
        AllRoomsAreaResponse allRoomsAreaResponse = new AllRoomsAreaResponse();
        allRoomsAreaResponse.setRoomsAreas(this.homeService.getAllRoomsArea(roomsList));

        return new ResponseEntity<>(allRoomsAreaResponse, HttpStatus.OK);
    }

    @PostMapping("/getHomeArea")
    public ResponseEntity<?> getHomeArea(@RequestBody @Valid HomeDto homeDto) {
        HomeAreaResponse homeAreaResponse = new HomeAreaResponse();
        homeAreaResponse.setName(homeDto.getName());
        homeAreaResponse.setDistrict(this.districtRepository.findDistrictByName(homeDto.getDistrict()));
        homeAreaResponse.setRooms(homeDto.getRoomList());
        homeAreaResponse.setHomeArea(this.homeService.getHomeArea(homeDto));

        return new ResponseEntity<>(homeAreaResponse, HttpStatus.OK);
    }

    @PostMapping("/getMaxRoom")
    public ResponseEntity<?> getMaxRoom(@RequestBody @Valid HomeDto homeDto) {
        MaxRoomResponse maxRoomResponse = new MaxRoomResponse();
        maxRoomResponse.setMaxRoom(this.homeService.getMaxRoom(homeDto));
        maxRoomResponse.setMaxRoomArea(this.homeService.getRoomArea(maxRoomResponse.getMaxRoom()));

        return new ResponseEntity<>(maxRoomResponse, HttpStatus.OK);
    }

    @PostMapping("/getHomePrice")
    public ResponseEntity<?> getHomePrice(@RequestBody @Valid HomeDto homeDto) {
        HomePriceResponse homePriceResponse = new HomePriceResponse();
        homePriceResponse.setHome(homeDto);
        homePriceResponse.setDistrict(this.districtRepository.findDistrictByName(homeDto.getDistrict()));
        homePriceResponse.setHomePrice(this.homeService.getHomePrice(homeDto));

        return new ResponseEntity<>(homePriceResponse, HttpStatus.OK);
    }

}
