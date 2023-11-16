package com.example.quiz;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.quiz.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@WebMvcTest(controllers = QuestionController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionController {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testGetQuestion() throws Exception{
        mockMvc.perform(get("/Question")).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].numberOfResponses", is(4)));

    }
}
