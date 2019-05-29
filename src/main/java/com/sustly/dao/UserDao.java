package com.sustly.dao;

import com.sustly.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author admin
 */
public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * findByUsername
     * @param username username
     * @return User
     */
    User findByUsername(String username);

    /**
     * findByUsernameAndPassword
     * @param username username
     * @param password password
     * @return User
     */
    User findByUsernameAndPassword(String username, String password);
}
