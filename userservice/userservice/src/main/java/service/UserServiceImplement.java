package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import domain.Role;
import domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import repository.UserRepository;
import repository.RoleRepository;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplement implements UserService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	@Override
	public User saveUser(User user) {
		log.info("Saving new user {}", user);
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {}", role);
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		log.info("Adding role {} to user {}", rolename, username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByRoleName(rolename);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}", username);
		return userRepository.findByUsername(username);
	}
	
	@Override
	public List<User> getUsers() {
		log.info("Fetching user list");
		return userRepository.findAll();
	}
}
