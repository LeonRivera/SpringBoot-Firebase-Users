package mx.com.leonrv.springbootfirebaseusers.models;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collectionName = "proveedoresCollection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Proveedor {
    
    @DocumentId
    String rfc;

    String razonSocial;
    String nombreContacto;
    String calle;
    Integer noInt;
    Integer noExt;
    String colonia;
    String localidadDelegacion;
    String entidad;
    String municipio;
    String pais;
    Integer codigoPostal;
    String correoElectronico;
    Long telefono;
    String estatus;

}
