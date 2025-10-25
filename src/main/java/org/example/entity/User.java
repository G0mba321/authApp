package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @EqualsAndHashCode.Exclude
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @EqualsAndHashCode.Exclude
    private String password;



    public User(String username, String Password) {
        this.username = username;
        this.password = Password;
        this.id = UUID.randomUUID();
    }
}
