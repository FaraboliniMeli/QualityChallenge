package br.com.marcello.QualityChallenge.service;

import br.com.marcello.QualityChallenge.dto.HomeDto;
import br.com.marcello.QualityChallenge.dto.RoomDto;

import java.util.List;
import java.util.Map;

public interface HomeService {

    Map<String, Double> getAllRoomsArea(List<RoomDto> roomsList);

    Double getHomeArea(HomeDto homeDto);

    RoomDto getMaxRoom(HomeDto homeDto);

    Double getRoomArea(RoomDto roomDto);

    Double getHomePrice(HomeDto homeDto);

}
