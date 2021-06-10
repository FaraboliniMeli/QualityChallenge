package br.com.marcello.QualityChallenge.controller;

import br.com.marcello.QualityChallenge.dto.DistrictDto;
import br.com.marcello.QualityChallenge.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/districts")
@RestController
public class HealthCheckDistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public DistrictDto healthCheck(@RequestParam (value = "name") String name) {

        return this.districtRepository.findDistrictByName(name);

    }

}
