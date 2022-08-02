package com.fms.trainings;

import com.fms.trainings.entities.Category;
import com.fms.trainings.restController.TrainingRestController;
import com.fms.trainings.service.CategoryService;
import com.fms.trainings.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(controllers = TrainingRestController.class)
class TrainingsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainingService trainingService;
	@MockBean
	private CategoryService categoryService;

	@Test
	public void getAllTrainingTest() throws Exception {
		mockMvc.perform(get("/training/all")).andExpect(status().isOk());

	}

}
