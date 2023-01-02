package spring_boot.service;

import org.springframework.stereotype.Service;
import spring_boot.dao.RoleRepository;
import spring_boot.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
