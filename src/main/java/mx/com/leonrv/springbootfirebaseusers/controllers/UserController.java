package mx.com.leonrv.springbootfirebaseusers.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //Returned
    @GetMapping
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticated(@RequestBody User user) {

        Map<String, Object> responseMap = new HashMap<>();

        Mono<User> userMono = userService.getByUsername(user.getUsername());

        User userBlocked = userMono.block();

        if (userBlocked == null) {
            responseMap.put("message", "El usuario no se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        // validate for same passwords
        if (userBlocked.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        Mono<User> savedUser = null;
        Map<String, Object> responseMap = new HashMap<>();

        Mono<User> byUsername = userService.getByUsername(user.getUsername());

        if(byUsername.block() != null){
            responseMap.put("message", "El usuario ya se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_MODIFIED);
        }

        try {
            savedUser = userService.save(user);
        } catch (Exception e) {
            responseMap.put("message", "Ocurrio un error :(");
            responseMap.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(savedUser.block(), HttpStatus.CREATED);
    }

    @PutMapping
    // public Mono<User> update(@RequestBody User user) {
    public ResponseEntity<?> update(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();

        Mono<User> byUsername = userService.getByUsername(user.getUsername());

        if(byUsername.block() == null){
            responseMap.put("message", "El usuario que intenta actualizar no se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(userService.save(user).block(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();

        Mono<User> byUsername = userService.getByUsername(user.getUsername());

        if(byUsername.block() == null){
            responseMap.put("message", "El usuario que intenta eliminar no se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }


        Mono<Void> delete = userService.delete(user);
        // System.out.println(delete.block());

        return new ResponseEntity<User>(byUsername.block(), HttpStatus.OK) ;
    }

}
