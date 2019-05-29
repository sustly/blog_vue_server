package com.sustly.service;

import com.sustly.entry.User;

/**
 *
 * @author admin
 */
public interface UserService {
    /**
     * findByUsername
     * @param username   username
     * @return User
     */
    User findByUsername(String username);

    /**
     * save
     * @param user user
     */
    void save(User user);

    /**
     *findByUsernameAndPassword
     * @param user user
     */
    User findByUsernameAndPassword(User user);
}
