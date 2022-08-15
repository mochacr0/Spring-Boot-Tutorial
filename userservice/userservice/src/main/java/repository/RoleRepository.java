package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRoleName(String name);
}
