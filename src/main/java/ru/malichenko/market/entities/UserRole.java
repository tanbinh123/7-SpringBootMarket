package ru.malichenko.market.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "users_roles")
public class UserRole {


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

}


//        не понимаю как создать составной ключ

//        primary key (user_id, role_id),
