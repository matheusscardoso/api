package br.home.msc.secutiry.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.home.msc.secutiry.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByLogin(String login);
	
	List<User> findAll();
}
