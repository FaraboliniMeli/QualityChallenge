package br.com.marcello.QualityChallenge.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DistrictControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getADistrict() throws Exception {
        this.mockMvc.perform(get("/districts")
                .param("name", "Tatuapé"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.districtName").value("Tatuapé"))
                .andExpect(jsonPath("$.squareMeterPrice").value(20.0));
    }

}
