package com.sustly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Sink.class)
@Slf4j
public class ConsumerController {

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message){
        log.info(message.getPayload());
    }
}
