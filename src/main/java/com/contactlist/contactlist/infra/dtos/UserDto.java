package com.contactlist.contactlist.infra.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String login, @NotBlank String password) {
}
