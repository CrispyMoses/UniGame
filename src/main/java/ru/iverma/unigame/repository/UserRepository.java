package ru.iverma.unigame.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iverma.unigame.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User getByLogin(String login);
}
