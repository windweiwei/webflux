package com.webflux.fluxdemo.handler;

import com.webflux.fluxdemo.model.User;
import com.webflux.fluxdemo.respository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
public class UserHandler {
    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }

    /**
     * 创建用户
     *
     * @param request s
     * @return res
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {

        Mono<User> user = request.bodyToMono(User.class);

        return user.flatMap(u -> {
            // 校验代码需要放在这里

            return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
                    .body(this.repository.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param request s
     * @return res
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");

        return this.repository.findById(id)
                .flatMap(
                        user -> this.repository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }



}
