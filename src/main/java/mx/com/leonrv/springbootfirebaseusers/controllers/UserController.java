package mx.com.leonrv.springbootfirebaseusers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.leonrv.springbootfirebaseusers.models.User;
import mx.com.leonrv.springbootfirebaseusers.services.IUserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping
    public Flux<User> findAll() {

        // System.out.println("===============Guardando==============");

        // User userToSave = new User("leon", "leon12345", 20);

        // Mono<User> uMono2 = userService.save(userToSave);

        // System.out.println("===============leyendo otravez==============");
        // uMono2.subscribe(val -> {
        //     System.out.println(val);
        // });

        // List<User> list1 = userService.findAll().collectList().block();

        // Flux<User> usuarios = userService.findAll();

        // Mono<User> uMono = userService.getByUsername("leon");
        // uMono.subscribe(val -> {
        //     System.out.println(val);
        // });
        

        // list1.forEach(System.out::println);

        return userService.findAll();
    }


    @PostMapping("/auth")
    public boolean authenticated(@RequestBody User user){


        System.out.println("En auth method");

        System.out.println(user.toString());
        Mono<User> userMono = userService.getByUsername(user.getUsername());

        User userBlocked = userMono.block();

        //validate
        if(userBlocked.getPassword().equals(user.getPassword())){
            return true;
        }
       
        return false;
    }

    @PostMapping
    public Mono<User> save(@RequestBody User user){
        return userService.save(user);
    }
    
    @PutMapping
    public Mono<User> update(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody User user){
        return userService.delete(user);
    }

    

}
