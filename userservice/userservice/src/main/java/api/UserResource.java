package api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.User;
import lombok.RequiredArgsConstructor;
import service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
	public final UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>>getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}
}
	