package ru.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUserName(String username);
}
