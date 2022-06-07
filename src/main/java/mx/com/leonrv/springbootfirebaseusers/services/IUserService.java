package mx.com.leonrv.springbootfirebaseusers.services;

import java.util.List;

import mx.com.leonrv.springbootfirebaseusers.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    

    Flux<User> findAll();

    Mono<User> save(User user);

    Mono<Void> delete(User user);

    Mono<User> getByUsername(String username);

}
