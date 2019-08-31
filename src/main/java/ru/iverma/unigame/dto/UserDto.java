package ru.iverma.unigame.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
	@JsonProperty("username")
	@NotNull
	private String username;
	@JsonProperty("password")
	@NotNull
	private String password;
	@JsonProperty("nickname")
	@NotNull
	private String nickname;
	@JsonProperty("name")
	@NotNull
	private String name;
}
