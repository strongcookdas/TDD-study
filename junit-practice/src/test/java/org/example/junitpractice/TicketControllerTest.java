package org.example.junitpractice;

import org.example.junitpractice.controller.TicketController;
import org.example.junitpractice.service.TicketingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TicketingService ticketingService;

    @DisplayName("WebMvcTest를 이용한 Controller Test")
    @Test
    public void ticketingControllerTest() throws Exception {
        // given
        String contents = "Test";

        mockMvc.perform(get("/ticket"))
                .andExpect(status().isOk())
                .andExpect(content().string(contents));
    }
}
