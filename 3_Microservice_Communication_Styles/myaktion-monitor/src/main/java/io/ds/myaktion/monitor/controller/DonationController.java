package io.ds.myaktion.monitor.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import io.ds.myaktion.monitor.dto.ReducedDonation;

@RestController
public class DonationController {
    @Autowired
    Set<SseEmitter> currentEmitters;
    
    @PostMapping("/donations")
    public void push(@RequestBody ReducedDonation donation)throws IOException {
        synchronized(currentEmitters) {
            for (SseEmitter emitter : currentEmitters) {
                if (emitter!=null) {
                    SseEventBuilder event = SseEmitter.event().data(donation); 
                    emitter.send(event);
                }
            }
        }
    }
}
