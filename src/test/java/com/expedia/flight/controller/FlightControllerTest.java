package com.expedia.flight.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEmptyWhenThereAreNoFlightsWithDepartureDatePlusMinus5HoursToTheGivenTime() throws Exception {
        String givenTime = "10:30PM";
        mockMvc.perform(get("/api/flights")
                .param("departure", givenTime))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void shouldNotReturnEmptyWhenThereAreFlightsWithDepartureDatePlusMinus5HoursToTheGivenTime() throws Exception {
        String givenTime = "3:30AM";
        mockMvc.perform(get("/api/flights")
                .param("departure", givenTime))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].flight").value("Air Canada 8099"));
    }

    @Test
    public void shouldReturnBadRequestWhenDepartureParameterIsNotPresent() throws Exception {
        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Required String parameter 'departure' is not present"));
    }

    @Test
    public void shouldReturnBadRequestWhenDepartureParameterIsInvalid() throws Exception {
        String givenTime = "3:30";
        mockMvc.perform(get("/api/flights")
                .param("departure", givenTime))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Invalid Time"));
    }
}
