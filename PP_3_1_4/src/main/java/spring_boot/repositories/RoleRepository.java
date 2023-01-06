package spring_boot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_boot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
