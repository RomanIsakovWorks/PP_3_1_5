package spring_boot.service;

import org.springframework.stereotype.Service;
import spring_boot.model.Role;
import spring_boot.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<Role>(roleRepository.findAll());
    }

}
