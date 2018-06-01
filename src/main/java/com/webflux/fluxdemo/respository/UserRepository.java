package com.webflux.fluxdemo.respository;

import com.webflux.fluxdemo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByAgeBetween(Integer start, Integer end);
}
