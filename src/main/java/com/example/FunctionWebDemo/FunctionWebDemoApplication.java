package com.example.FunctionWebDemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class FunctionWebDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        HandlerFunction hello = request ->
                ServerResponse.ok().body(Mono.just("hello"), String.class);

        RouterFunction router = route(GET("/"), hello);

        HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);

        HttpServer.create(8080).newHandler(new ReactorHttpHandlerAdapter(httpHandler)).block();

        Thread.currentThread().join();
    }
}
