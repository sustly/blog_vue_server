package com.sustly.service.impl;

import com.sustly.dao.UserDao;
import com.sustly.entry.User;
import com.sustly.service.UserService;
import com.sustly.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyue
 * @date 2019/5/29 9:35
 */
@Service
@Transactional(rollbackForClassName = {"Exception.class"})
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), Md5Util.encrypt(user.getPassword()));
    }
}
