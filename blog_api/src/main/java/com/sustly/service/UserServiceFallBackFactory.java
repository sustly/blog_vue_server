package com.sustly.service;

import com.sustly.entry.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:28
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User register(User user) {
                return null;
            }

            @Override
            public User login(User user) {
                return null;
            }
        };
    }
}
