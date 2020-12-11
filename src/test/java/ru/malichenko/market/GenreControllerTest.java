package ru.malichenko.market;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private GenreService genreService;
//
//    @Test
//    @WithMockUser(username = "Bob", authorities = "USER")
//    public void getAllGenresTest() throws Exception {
////        ProjectionFactory factory = new SpelAwareProxyProjectionFactory(); // для интерфейсов
////        BookDto bookDto = factory.createProjection(BookDto.class);
////        bookDto.setTitle("Harry Potter");
//
//        Genre fantasy = new Genre();
//        fantasy.setId(1L);
//        fantasy.setTitle("Fantasy");
//        List<Genre> allGenres = new ArrayList<>(Arrays.asList(
//                fantasy
//        ));
//        given(genreService.findAll()).willReturn(allGenres);
//
//        mvc.perform(get("/api/v1/genres")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].title", is(allGenres.get(0).getTitle())));
//    }
}
