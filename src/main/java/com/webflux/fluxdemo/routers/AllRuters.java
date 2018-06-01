package com.webflux.fluxdemo.routers;

import com.webflux.fluxdemo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;


@Configuration
public class AllRuters {
    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                RequestPredicates.path("/user"),
                RouterFunctions.route(RequestPredicates.GET("/"), handler::getAllUser))
                .andRoute(RequestPredicates.POST("/save "), handler::createUser)
                .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById);
    }
}
