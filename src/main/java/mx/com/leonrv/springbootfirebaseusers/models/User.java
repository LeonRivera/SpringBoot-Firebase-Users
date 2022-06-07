package mx.com.leonrv.springbootfirebaseusers.models;

import java.io.Serializable;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collectionName = "usersCollection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable{
    
    @DocumentId
    String username;

    String password;

    Integer age;

}
