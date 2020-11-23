package ng.dominic.bb.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When rolling d6, expect status ok and expect to return json")
    void rollSpecific() throws Exception {
        mockMvc.perform(get("/roll/d6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("When rolling 2 d6, expect status ok, expect rolled results to be a number and numeric diceValue")
    void roll() throws Exception {
        String jsonBody = """
                {
                    dice: [
                        "d6",
                        "d6",
                    ]
                }""";
        String rolledResult = "$.values[%s].value0";
        String diceValue = "$.values[%s].value1";
        mockMvc.perform(post("/roll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.values").isArray())
                .andExpect(jsonPath(rolledResult, 0).isNumber())
                .andExpect(jsonPath(rolledResult, 1).isNumber())
                .andExpect(jsonPath(diceValue, 0).value(containsString("NUMERIC")))
                .andExpect(jsonPath(diceValue, 1).value(containsString("NUMERIC")));
    }

    @Test
    void diceChoices() throws Exception {
        mockMvc.perform(get("/choices"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}