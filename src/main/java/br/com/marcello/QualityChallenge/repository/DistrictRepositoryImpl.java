package br.com.marcello.QualityChallenge.repository;

import br.com.marcello.QualityChallenge.dto.DistrictDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {


    @Override
    public DistrictDto findDistrictByName(String districtName) {
        List<DistrictDto> districts = this.initJsonRepo();

        return districts.stream()
                .filter(districtDto -> districtDto.getDistrictName().equalsIgnoreCase(districtName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(districtName + " district not found in repository"));
    }

    private List<DistrictDto> initJsonRepo() {

        File jsonFile = null;

        try {
            jsonFile = ResourceUtils.getFile("classpath:districts.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file cannot be found", e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDto>> typeReference = new TypeReference<List<DistrictDto>>() {};
        List<DistrictDto> districts = null;

        try {
            districts = objectMapper.readValue(jsonFile, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse JSON file to Districts Model", e);
        }

        return districts;
    }


}
