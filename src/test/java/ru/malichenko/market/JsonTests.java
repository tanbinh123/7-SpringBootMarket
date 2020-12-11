package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.entities.ProductEntity;

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
                .extractingJsonPathStringValue("$.id").isEqualTo(1L);
        assertThat(jackson.write(productEntity))
                .hasJsonPathNumberValue("$.title")
                .extractingJsonPathStringValue("$.title").isEqualTo("Milk");
        assertThat(jackson.write(productEntity))
                .hasJsonPathNumberValue("$.price")
                .extractingJsonPathStringValue("$.price").isEqualTo(123);

    }

//    @Test
//    public void jsonSerializationTest() throws Exception {
//        RoleEntity role = new RoleEntity();
//        role.setId(1L);
//        role.setName("USER");
//        // {
//        //   "id": 1,
//        //   "name": "USER"
//        // }
//        assertThat(this.jackson.write(role)).hasJsonPathNumberValue("$.id");
//        assertThat(this.jackson.write(role)).extractingJsonPathStringValue("$.name").isEqualTo("USER");
//    }
//
//    @Test
//    public void jsonDeserializationTest() throws Exception {
//        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
//        RoleEntity realRole = new RoleEntity();
//        realRole.setId(2L);
//        realRole.setName("ADMIN");
//
//        assertThat(this.jackson.parse(content)).isEqualTo(realRole);
//        assertThat(this.jackson.parseObject(content).getName()).isEqualTo("ADMIN");
//    }
}