package ru.malichenko.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malichenko.market.entities.ProfileEntity;
import ru.malichenko.market.repositories.ProfileRepository;
import ru.malichenko.market.services.ProfileService;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private ProfileService profileService;

    @MockBean
    private ProfileRepository profileRepository;

    @Test
    public void findOneUserTest() {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName("John");
        profileEntity.setEmail("john@mail.ru");

        Mockito.doReturn(Optional.of(profileEntity))
                .when(profileRepository)
                .findByUsername("John");

        ProfileEntity userJohn = profileService.findByUsername("John").get();
        Assertions.assertNotNull(userJohn);
        Assertions.assertEquals("john@mail.ru", userJohn.getEmail());
        Mockito.verify(profileRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
//        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.any(String.class));
    }
}
