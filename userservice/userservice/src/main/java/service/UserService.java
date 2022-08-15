package service;

import java.util.List;

import domain.Role;
import domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String rolename);
	User getUser(String username);
	List<User>getUsers();
}
