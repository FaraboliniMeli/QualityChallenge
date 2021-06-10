package br.com.marcello.QualityChallenge.unit;

import br.com.marcello.QualityChallenge.dto.DistrictDto;
import br.com.marcello.QualityChallenge.repository.DistrictRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DistrictRepositoryTest {

    static DistrictDto district;

    @InjectMocks
    DistrictRepositoryImpl districtRepository;

    @BeforeAll
    public static void setup() {
        district = new DistrictDto();
        district.setDistrictName("Vila Regente Feijó");
        district.setSquareMeterPrice(10.5);
    }

    @Test
    public void shouldFindDistrict() {
        String districtNameToBeFound = "Vila Regente Feijó";
        DistrictDto districtFound = this.districtRepository.findDistrictByName(districtNameToBeFound);
        Assertions.assertEquals(districtNameToBeFound, districtFound.getDistrictName(), "Vila Regente Feijó district should be found.");
    }

    @Test
    public void shouldThrowNoSuchElementException() {
        String districtThatNotExists = "Penha";
        Assertions.assertThrows(NoSuchElementException.class,
                () -> this.districtRepository.findDistrictByName(districtThatNotExists), "Should throw No Such Element Exception");
    }

}
