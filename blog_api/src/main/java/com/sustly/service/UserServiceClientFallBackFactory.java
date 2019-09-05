package com.sustly.service;

import com.sustly.entry.User;
import com.sustly.dto.ResponseMsg;
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
            public ResponseMsg register(User user) {
                User userBack = new User(0,null,null, null, null);
                return ResponseMsg.onOk(userBack,false);
            }

            @Override
            public ResponseMsg login(User user) {
                User userBack = new User(0,null,null, null, null);
                return ResponseMsg.onOk(userBack,false);
            }
        };
    }
}
