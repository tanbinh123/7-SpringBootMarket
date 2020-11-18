package ru.malichenko.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.RoleEntity;
import ru.malichenko.market.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity findByRoleName(String role_user) {
        return roleRepository.findRoleEntitiesByName(role_user);
    }

    public List<RoleEntity> getRole(String role){
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(findByRoleName(role));
        return roleEntityList;
    }

}