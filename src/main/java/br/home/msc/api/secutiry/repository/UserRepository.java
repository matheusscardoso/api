package br.home.msc.api.secutiry.repository;

import br.home.msc.api.secutiry.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    List<User> findAll();
}
