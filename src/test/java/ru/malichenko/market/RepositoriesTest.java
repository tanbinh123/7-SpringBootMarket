package ru.malichenko.market;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
//    @Autowired
//    private GenreRepository genreRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void genreRepositoryTest() {
//        Genre genre = new Genre();
//        genre.setTitle("Fiction");
//        entityManager.persist(genre);
//        entityManager.flush();
//
//        List<Genre> genresList = genreRepository.findAll();
//
//        Assertions.assertEquals(2, genresList.size());
//        Assertions.assertEquals("Fiction", genresList.get(1).getTitle());
//    }
//
//    @Test
//    public void initDbTest() {
//        List<Genre> genresList = genreRepository.findAll();
//        Assertions.assertEquals(1, genresList.size());
//    }
}
