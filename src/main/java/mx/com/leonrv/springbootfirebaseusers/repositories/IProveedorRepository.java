package mx.com.leonrv.springbootfirebaseusers.repositories;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;

import mx.com.leonrv.springbootfirebaseusers.models.Proveedor;

@Repository
public interface IProveedorRepository extends FirestoreReactiveRepository<Proveedor>{}
