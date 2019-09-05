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
                return ResponseMsg.onOk("该用户名已被占用！",false);
            }

            @Override
            public ResponseMsg login(User user) {
                return ResponseMsg.onOk("用户不存在！",false);
            }
        };
    }
}
