package ru.iverma.unigame.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.iverma.unigame.dto.UserDto;
import ru.iverma.unigame.service.UserService;

import javax.validation.Valid;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public String registerUser(@RequestBody @Valid UserDto dto) {
		Long userId = userService.registerUser( dto);
		return "yah, Success, i register user with id " + userId;
	}

	@PostMapping(value = "/login")
	public String login(@RequestBody UserDto dto) {
		userService.checkUserLogin(dto);
		return "password is correct";
	}
}
