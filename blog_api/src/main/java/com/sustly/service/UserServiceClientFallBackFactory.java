package com.sustly.service;

import com.google.common.collect.Lists;
import com.sustly.entry.User;
import com.sustly.util.ResponseMsg;
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
            public ResponseMsg<User> register(User user) {
                User userBack = new User(0,null,null, null, null);
                return ResponseMsg.onOk(userBack, Lists.newArrayList(),false);
            }

            @Override
            public ResponseMsg<User> login(User user) {
                User userBack = new User(0,null,null, null, null);
                return ResponseMsg.onOk(userBack, Lists.newArrayList(),false);
            }
        };
    }
}
