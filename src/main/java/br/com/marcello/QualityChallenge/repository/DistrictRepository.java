package br.com.marcello.QualityChallenge.repository;

import br.com.marcello.QualityChallenge.dto.DistrictDto;

public interface DistrictRepository {

    DistrictDto findDistrictByName(String districtName);

}
