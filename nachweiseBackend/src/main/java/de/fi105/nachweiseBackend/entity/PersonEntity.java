package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "person")
@Entity
@Data
public class PersonEntity {

    @Id
    @Column(name = "idPerson")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @Column(name = "passwordhash")
    private String passwordHash;

    @Column(name = "rolle")
    private int role;

    @Column(name = "vorname")
    private String prename;

    @Column(name = "nachname")
    private String lastname;

}
