package com.sustly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@EnableBinding(Source.class)
public class ProviderController {

    private final MessageChannel output;

    @Autowired
    public ProviderController(MessageChannel output) {
        this.output = output;
    }

    @GetMapping("/send")
    public void send(){
        output.send(MessageBuilder.withPayload(UUID.randomUUID().toString()).build());
    }
}