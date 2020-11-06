package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.Profile;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String birthday;
    private String gender;
    private String city;

    public ProfileDto(Profile p) {
        this.id = p.getId();
        this.name = p.getName();
        this.surname = p.getSurname();
        this.phone = p.getPhone();
        this.email = p.getEmail();
        this.birthday = p.getBirthday();
        this.gender = p.getGender();
        this.city = p.getCity();
    }
}