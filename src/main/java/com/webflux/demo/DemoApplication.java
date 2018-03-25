package com.webflux.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class DemoApplication {

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return route(GET("/book").and(accept(MediaType.APPLICATION_JSON)), serverRequest -> {
            Book book1 = new Book();
            book1.setTitie("토끼와거북이");
            book1.setWriter("ahn");
            Book book2 = new Book();
            book2.setTitie("토끼와거북이2");
            book2.setWriter("ahn2");

            Flux<Book> books = Flux.just(book1,book2);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(books,Book.class);
        });
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
