package br.home.msc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.home.msc.secutiry.entity.User;
import br.home.msc.secutiry.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping()
	public String getMethodName() {
		return "running";
	}
	
	@GetMapping("users")
	public List<User> getMethodName1() {
		return repo.findAll();
	}

}
