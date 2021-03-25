package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import ru.malichenko.market.dto.CategoryDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoriesTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(username = "User", roles = "USER")
    public void getCategoriesListTest(){
        List<CategoryDto> categories = restTemplate.getForObject("/api/v1/categories", List.class);
        assertThat(categories).isNotNull();
        assertThat(categories).isNotEmpty();
    }
}
