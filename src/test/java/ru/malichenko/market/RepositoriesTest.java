package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import ru.malichenko.market.entities.ProductEntity;
import ru.malichenko.market.entities.ProfileEntity;
import ru.malichenko.market.repositories.CategoryRepository;
import ru.malichenko.market.repositories.ProductRepository;
import ru.malichenko.market.repositories.ProfileRepository;
import ru.malichenko.market.utils.ProductFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void initDbTest() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(38, productEntityList.size());
    }

    @Test
    public void genreRepositoryTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle("phone");
        productEntity.setPrice(100);
        productEntity.setCategoryEntity(categoryRepository.findAll().get(1));
        entityManager.persist(productEntity);
        entityManager.flush();

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(39, productEntityList.size());
        assertEquals("phone", productEntityList.get(38).getTitle());
    }

    @Test
    public void checkAllProductWithSpecTest() {
        ProductFilter filter = getFilterWithSpec(null, 0, null, "1");
        Page<ProductEntity> content = productRepository.findAll(filter.getSpec(), PageRequest.of(0, 5));
        assertNotNull(content);
        assertNotNull(content.getContent());
        assertEquals(35, content.getTotalElements());
    }

    private ProductFilter getFilterWithSpec(String title, Integer min, Integer max, String category) {
        Map<String, String> params = new HashMap<>();
        if (title != null) params.put("title", title);
        if (min != null) params.put("min_price", min.toString());
        if (max != null) params.put("max_price", max.toString());
        if (category != null) params.put("category", category);

        return new ProductFilter(params);
    }

    @Test
    public void  checkProfileByUsername(){
        Optional<ProfileEntity> profileEntity = profileRepository.findByUsername("user");
        assertNotNull(profileEntity);
        assertEquals("1984",profileEntity.get().getBirthday());
    }

}
