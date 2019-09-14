package com.sustly.service;

import com.sustly.entry.User;
import com.sustly.dto.ResponseMsg;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:28
 */
@Component
@Slf4j
public class UserServiceClientFallBackFactory implements FallbackFactory<UserClientService> {

    @Override
    public UserClientService create(Throwable throwable) {
        return new UserClientService() {
            @Override
            public ResponseMsg register(User user) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！！");
            }

            @Override
            public ResponseMsg login(User user) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }
        };
    }
}
