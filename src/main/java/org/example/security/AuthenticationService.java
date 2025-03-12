package org.example.security;

import org.example.exception.AuthenticationException;
import org.example.exception.RegistrationException;
import org.example.model.User;

public interface AuthenticationService {

    User login(String userName, String password) throws AuthenticationException;

    User register(String userName, String password) throws RegistrationException;

}
