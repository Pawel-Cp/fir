package org.example.security;

import org.example.exception.AuthenticationException;
import org.example.exception.RegistrationException;
import org.example.lib.Inject;
import org.example.model.User;
import org.example.service.UserService;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String userName, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public User register(String userName, String password) throws RegistrationException {
        return null;
    }
}
