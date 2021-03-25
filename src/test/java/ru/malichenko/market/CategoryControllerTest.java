package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.services.CategoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    @WithMockUser(username = "Bob", authorities = "USER")
    public void getAllCategoriesTest() throws Exception {
//        ProjectionFactory factory = new SpelAwareProxyProjectionFactory(); // для интерфейсов
//        BookDto bookDto = factory.createProjection(BookDto.class);
//        bookDto.setTitle("Harry Potter");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(10L);
        categoryEntity.setTitle("phone");
        List<CategoryEntity> allCategories = new ArrayList<>(Collections.singletonList(categoryEntity));
        given(categoryService.findAll()).willReturn(allCategories);

        mvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allCategories.get(0).getTitle())));
    }
}
