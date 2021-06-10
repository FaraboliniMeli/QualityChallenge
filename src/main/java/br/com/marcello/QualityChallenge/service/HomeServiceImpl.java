package br.com.marcello.QualityChallenge.service;

import br.com.marcello.QualityChallenge.dto.DistrictDto;
import br.com.marcello.QualityChallenge.dto.HomeDto;
import br.com.marcello.QualityChallenge.dto.RoomDto;
import br.com.marcello.QualityChallenge.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    public DistrictRepository districtRepository;

    @Override
    public Map<String, Double> getAllRoomsArea(List<RoomDto> roomsList) {
        Map<String, Double> roomsAndAreas = new HashMap<>();

        roomsList.forEach(
                roomDto -> roomsAndAreas.put(roomDto.getName(), this.getRoomArea(roomDto))
        );

        return  roomsAndAreas;
    }

    @Override
    public Double getHomePrice(HomeDto homeDto) {
        DistrictDto district = this.districtRepository.findDistrictByName(homeDto.getDistrict());
        Double homeArea = this.getHomeArea(homeDto);

        return homeArea * district.getSquareMeterPrice();
    }

    @Override
    public Double getHomeArea(HomeDto homeDto) {
        return homeDto.getRoomList()
                .stream()
                .mapToDouble(this::getRoomArea)
                .sum();
    }

    @Override
    public RoomDto getMaxRoom(HomeDto homeDto) {
        Double maxArea = homeDto.getRoomList()
                .stream()
                .mapToDouble(this::getRoomArea)
                .max()
                .getAsDouble();

        return homeDto.getRoomList()
                .stream()
                .filter(roomDto -> this.getRoomArea(roomDto).equals(maxArea))
                .findFirst()
                .get();
    }

    @Override
    public Double getRoomArea(RoomDto roomDto) {
        return roomDto.getRoomLength() * roomDto.getRoomWidth();
    }
}
