package io.ds.myaktion.monitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@SpringBootApplication
public class MyaktionMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyaktionMonitorApplication.class, args);
	}

	@Bean
	public Set<SseEmitter> initEmitterSet() {
	return Collections.synchronizedSet(new HashSet<SseEmitter>());
	}
}
