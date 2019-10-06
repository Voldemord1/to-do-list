package tech.artos.service.interfaces;

import tech.artos.model.User;

import java.util.Optional;

public interface UserService {

    void addUser(String email, String password, String role);

    Optional<User> getUserByEmail(String email);
}
