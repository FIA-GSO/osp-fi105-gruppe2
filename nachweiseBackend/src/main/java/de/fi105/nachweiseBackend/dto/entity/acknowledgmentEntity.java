package de.fi105.nachweiseBackend.dto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "Quittierung")
@Entity
public class acknowledgmentEntity {

    @Id
    @Column(name = "idQuittierung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nachweisId")
    private Long proofId;

    @Column(name = "quittiert")
    private boolean acknowlegded;

    @Column(name = "kommentar")
    private String comment;
}
