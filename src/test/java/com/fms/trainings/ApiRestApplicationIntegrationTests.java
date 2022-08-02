package com.fms.trainings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ApiRestApplicationIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnListOfTrainings() throws Exception {
        mockMvc.perform(get("/training/all")).andExpect(status().isOk()).andExpect(jsonPath("$[0].price", is(2800.)));


    }
}
