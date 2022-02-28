package Os2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Os2.backend.model.User;

@RestController
public class UserController {
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		return null;
	}

}
