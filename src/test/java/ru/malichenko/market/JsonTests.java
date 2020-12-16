package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.entities.ProductEntity;
import ru.malichenko.market.entities.RoleEntity;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<ProductEntity> jackson;

    @Test
    public void jsonSerializationProductTest() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setTitle("Milk");
        productEntity.setPrice(123);
        productEntity.setCategoryEntity(new CategoryEntity());
        // {
        //   "id": 1,
        //   "name": "Milk",
        //   "price": 123,
        //   "category": null
        // }
        assertThat(jackson.write(productEntity))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(jackson.write(productEntity))
                .hasJsonPathStringValue("$.title")
                .extractingJsonPathStringValue("$.title").isEqualTo("Milk");
        assertThat(jackson.write(productEntity))
                .hasJsonPathNumberValue("$.price")
                .extractingJsonPathNumberValue("$.price").isEqualTo(123);

    }


    @Autowired
    private JacksonTester<RoleEntity> jacksonRole;

    @Test
    public void jsonSerializationTest() throws Exception {
        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setName("USER");
        // {
        //   "id": 1,
        //   "name": "USER"
        // }
        assertThat(jacksonRole.write(role)).hasJsonPathNumberValue("$.id")
        .extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        RoleEntity realRole = new RoleEntity();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(jacksonRole.parse(content)).isEqualTo(realRole);
        assertThat(jacksonRole.parseObject(content).getName()).isEqualTo("ADMIN");
    }
}