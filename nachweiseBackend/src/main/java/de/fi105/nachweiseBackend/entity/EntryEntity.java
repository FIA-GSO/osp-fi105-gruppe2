package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "eintrag")
@Entity
public class EntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEintrag")
    private Integer id;

    @Column(name = "ausgefuehrteArbeit")
    private String workDone;

    @Column(name = "einzelSunden")
    private Integer hours;

}
