package spring_boot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
