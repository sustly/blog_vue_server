package com.sustly.service;

import com.sustly.entry.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:28
 */
@Component
public class UserServiceClientFallBackFactory implements FallbackFactory<UserClientService> {

    @Override
    public UserClientService create(Throwable throwable) {
        return new UserClientService() {
            @Override
            public User register(User user) {
                return new User(0,null,null, null, null);
            }

            @Override
            public User login(User user) {
                return new User(0,null,null, null, null);
            }
        };
    }
}
