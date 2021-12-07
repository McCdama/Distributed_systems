package io.ds.myaktion.monitor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Controller
public class SSEEndpointController {
    @Autowired
    private Set<SseEmitter> currentEmitters;

    @GetMapping("/monitor")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();
        emitter.onTimeout(() -> {
            emitter.complete();
            currentEmitters.remove(emitter);
        });
        currentEmitters.add(emitter);
        return emitter;
    }

    // @PostMapping("/donations")
    // public void push(@RequestBody ReducedDonation donation) throws IOException {
    //     synchronized (currentEmitters) {
    //         for (SseEmitter emitter : currentEmitters) {
    //             if (emitter != null) {
    //                 SseEventBuilder event = SseEmitter.event().data(donation);
    //                 emitter.send(event);
    //             }
    //         }
    //     }
    // }
}
