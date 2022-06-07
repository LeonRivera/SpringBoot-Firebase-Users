package mx.com.leonrv.springbootfirebaseusers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.leonrv.springbootfirebaseusers.models.User;
import mx.com.leonrv.springbootfirebaseusers.repositories.IUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    @Autowired(required = false)
    IUserRepository userRepository;

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<Void> delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public Mono<User> getByUsername(String username) {
        return userRepository.findById(username);
    }

    // public String saludoService() {
    //     return "hola desde el servicio";
    // }

}
