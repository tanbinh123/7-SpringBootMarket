package ru.malichenko.market;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    @WithMockUser(username = "Bob", roles = "USER")
//    public void fullRestTest() {
//        // Spring page class ...
//        List<Genre> genres = restTemplate.getForObject("/api/v1/genres", List.class);
//        assertThat(genres).isNotNull();
//        assertThat(genres).isNotEmpty();
//    }
}
