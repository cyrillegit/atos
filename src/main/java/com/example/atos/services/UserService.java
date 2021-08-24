package com.example.atos.services;

import com.example.atos.exceptions.AtosException;
import com.example.atos.models.User;

public interface UserService {
    /**
     * register an user
     * @param user to be registered
     * @return the registered user
     * @throws AtosException if something goes wrong
     */
    User register(User user) throws AtosException;

    /**
     * display details of user
     * @param id of the user
     * @return the user with the corresponding id
     */
    User display(Integer id);
}
