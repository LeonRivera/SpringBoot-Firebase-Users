package mx.com.leonrv.springbootfirebaseusers.services;

import mx.com.leonrv.springbootfirebaseusers.models.Proveedor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProveedorService {
    Flux<Proveedor> findAll();

    Mono<Proveedor> save(Proveedor proveedor);

    Mono<Void> delete(Proveedor proveedor);

    Mono<Void> deleteByRfc(String rfc);

    Mono<Proveedor> getByRfc(String rfc);
}
