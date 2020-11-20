package ng.dominic.bb.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When rolling d6, expect status ok and expect to return json")
    void rollSpecific() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/roll/d6"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // TODO: 20/11/2020 should Expect that $.values[*].value0 are numbers between 1 and D6.faces(), and
    // $.values[*].value1 are "NUMERIC"
    @Test
    @DisplayName("When rolling 2 d6, expect status ok")
    void roll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/roll")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            dice: [
                                "d6",
                                "d6",
                            ]
                        }""")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.values").isArray());
    }

    @Test
    void diceChoices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/choices"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}