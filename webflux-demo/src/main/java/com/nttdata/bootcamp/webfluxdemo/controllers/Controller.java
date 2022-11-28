package com.nttdata.bootcamp.webfluxdemo.controllers;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.webfluxdemo.reposiroty.Subscriber;

import reactor.core.publisher.Flux;

@RestController
public class Controller {

	@GetMapping(path = "/numbers", produces = "text/event-stream")
	public Flux<Integer> numbers() {

		Flux<Integer> messageSender = Flux.range(1, 30).delayElements(Duration.ofSeconds(1));

		return messageSender;
	}

	@GetMapping(path = "/numbers-with-subscribers", produces = "text/event-stream")
	public Flux<Integer> numbersWithSubscribers() {
		Flux<Integer> flux = Flux.range(1, 30).delayElements(Duration.ofSeconds(1));

		flux.subscribe(n -> Subscriber.print(n)); // Suscriptor 1
		flux.subscribe(n -> System.out.println("Subscriber 2: " + n)); // Suscriptor 2

		return flux; // Suscriptor 3
	}
}