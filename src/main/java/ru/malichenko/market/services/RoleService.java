package ru.malichenko.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.Role;
import ru.malichenko.market.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(String role_user) {
        return roleRepository.findRoleByName(role_user);
    }

    public List<Role> getRole(String role){
        List<Role> roleList = new ArrayList<>();
        roleList.add(findByRoleName(role));
        return roleList;
    }

}