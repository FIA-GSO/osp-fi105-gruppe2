package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "quittierung")
@Entity
public class AcknowledgmentEntity {

    @Id
    @Column(name = "idQuittierung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nachweisId")
    private Integer proofId;

    @Column(name = "kommentar")
    private String comment;
}
