package br.com.marcello.QualityChallenge.controller;

import br.com.marcello.QualityChallenge.dto.*;
import br.com.marcello.QualityChallenge.exceptionHandling.ApiExceptionResponse;
import br.com.marcello.QualityChallenge.repository.DistrictRepository;
import br.com.marcello.QualityChallenge.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Home Controller", description = "API for Home valuation service")
@Validated
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private DistrictRepository districtRepository;

    @Operation(summary = "Get area for all home's rooms.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Areas well calculated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AllRoomsAreaResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid JSON Array of RoomDTO.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server error.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    })
    })
    @PostMapping("/getAllRoomsArea")
    public ResponseEntity<?> getAllRoomsArea(@RequestBody List<@Valid RoomDto> roomsList) {
        AllRoomsAreaResponse allRoomsAreaResponse = new AllRoomsAreaResponse();
        allRoomsAreaResponse.setRoomsAreas(this.homeService.getAllRoomsArea(roomsList));

        return new ResponseEntity<>(allRoomsAreaResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get home total area.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Home area well calculated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HomeAreaResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid JSON Home.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server error.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    })
    })
    @PostMapping("/getHomeArea")
    public ResponseEntity<?> getHomeArea(@RequestBody @Valid HomeDto homeDto) {
        HomeAreaResponse homeAreaResponse = new HomeAreaResponse();
        homeAreaResponse.setName(homeDto.getName());
        homeAreaResponse.setDistrict(this.districtRepository.findDistrictByName(homeDto.getDistrict()));
        homeAreaResponse.setRooms(homeDto.getRoomList());
        homeAreaResponse.setHomeArea(this.homeService.getHomeArea(homeDto));

        return new ResponseEntity<>(homeAreaResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get largest room.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Largest room well calculated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MaxRoomResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid JSON Home.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server error.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    })
    })
    @PostMapping("/getMaxRoom")
    public ResponseEntity<?> getMaxRoom(@RequestBody @Valid HomeDto homeDto) {
        MaxRoomResponse maxRoomResponse = new MaxRoomResponse();
        maxRoomResponse.setMaxRoom(this.homeService.getMaxRoom(homeDto));
        maxRoomResponse.setMaxRoomArea(this.homeService.getRoomArea(maxRoomResponse.getMaxRoom()));

        return new ResponseEntity<>(maxRoomResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get total price for house according to district square meter price.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Home price well calculated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HomePriceResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid JSON Home.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server error.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    })
    })
    @PostMapping("/getHomePrice")
    public ResponseEntity<?> getHomePrice(@RequestBody @Valid HomeDto homeDto) {
        HomePriceResponse homePriceResponse = new HomePriceResponse();
        homePriceResponse.setHome(homeDto);
        homePriceResponse.setDistrict(this.districtRepository.findDistrictByName(homeDto.getDistrict()));
        homePriceResponse.setHomePrice(this.homeService.getHomePrice(homeDto));

        return new ResponseEntity<>(homePriceResponse, HttpStatus.OK);
    }

}
