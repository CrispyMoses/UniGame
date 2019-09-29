package ru.iverma.unigame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iverma.unigame.dto.UserDto;
import ru.iverma.unigame.entity.User;
import ru.iverma.unigame.exception.AuthorizeException;
import ru.iverma.unigame.repository.UserRepository;

import javax.transaction.Transactional;
import static ru.iverma.unigame.util.CryptoUtills.generateSaltHash;
import static ru.iverma.unigame.util.CryptoUtills.isCorrectPassword;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional
	public Long registerUser(UserDto dto) {
		if (repository.getByLogin(dto.getUsername()) != null) {
			throw new AuthorizeException("Логин занят");
		}
		var user = new User();
		user.setLogin(dto.getUsername());
		user.setPassword(generateSaltHash(dto.getPassword()));
		user.setNickname(dto.getNickname());
		user.setName(dto.getName());
		repository.save(user);
		return user.getId();
	}

	public void checkUserLogin(UserDto dto) {
		var user = repository.getByLogin(dto.getUsername());
		if (user == null) {
			throw new IllegalArgumentException("Пользователя с таким логином не существует");
		}
		if (!isCorrectPassword(dto.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("Пароль неверный");
		}
	}
}
