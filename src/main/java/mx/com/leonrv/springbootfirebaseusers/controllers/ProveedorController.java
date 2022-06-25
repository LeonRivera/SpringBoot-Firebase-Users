package mx.com.leonrv.springbootfirebaseusers.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.leonrv.springbootfirebaseusers.models.Proveedor;
import mx.com.leonrv.springbootfirebaseusers.services.IProveedorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    
    @Autowired
    IProveedorService proveedorService;


    @GetMapping
    public Flux<Proveedor> findAll(){
        return proveedorService.findAll();
    }

    @GetMapping("/{rfc}")
    public ResponseEntity<?> findByRfc(@PathVariable String rfc) {
        Map<String, Object> responseMap = new HashMap<>();
        Mono<Proveedor> proveedorMono = proveedorService.getByRfc(rfc);

        Proveedor proveedorBlocked = proveedorMono.block();

        if(proveedorBlocked == null){
            responseMap.put("message", "El proveedor no se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Proveedor>(proveedorBlocked, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Proveedor proveedor){
        Mono<Proveedor> savedProveedor = null;
        Map<String, Object> responseMap = new HashMap<>();

        Mono<Proveedor> byRfc = proveedorService.getByRfc(proveedor.getRfc());

        if(byRfc.block() != null){
            responseMap.put("message", "El proveedor ya se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        try{
            savedProveedor = proveedorService.save(proveedor);
        }catch(Exception e){
            responseMap.put("message", "Ocurrio un error :(");
            responseMap.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Proveedor>(savedProveedor.block(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Proveedor proveedor){
        Map<String, Object> responseMap = new HashMap<>();

        Mono<Proveedor> byRfc = proveedorService.getByRfc(proveedor.getRfc());

        if(byRfc.block() == null){
            responseMap.put("message", "El proveedor que intentas actualizar no se encuentra registraod");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Proveedor>(proveedorService.save(proveedor).block(), HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Proveedor proveedor){
        Map<String, Object> responseMap = new HashMap<>();

        Mono<Proveedor> byRfc = proveedorService.getByRfc(proveedor.getRfc());

        if(byRfc.block() == null){
            responseMap.put("message", "El proveedor que intenta eliminar no se encuentra registrado");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }

        proveedorService.delete(proveedor).block();

        return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
    }

    

    
}
