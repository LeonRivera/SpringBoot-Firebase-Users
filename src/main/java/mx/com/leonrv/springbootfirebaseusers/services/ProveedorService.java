package mx.com.leonrv.springbootfirebaseusers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.leonrv.springbootfirebaseusers.models.Proveedor;
import mx.com.leonrv.springbootfirebaseusers.repositories.IProveedorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProveedorService implements IProveedorService{
    

    @Autowired(required = false)
    IProveedorRepository proveedorRepository;

    @Override
    public Flux<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Mono<Proveedor> save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Mono<Void> delete(Proveedor proveedor) {
        return proveedorRepository.delete(proveedor);
    }

    @Override
    public Mono<Proveedor> getByRfc(String rfc) {
        return proveedorRepository.findById(rfc);
    }

    @Override
    public Mono<Void> deleteByRfc(String rfc) {
        return proveedorRepository.deleteById(rfc);
    }



}
