package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "Eintrag")
@Entity
public class EntryEntity {

    @Id
    @Column(name = "idEintrag")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ausgefuehrteArbeit")
    private String doneWork;

    @Column(name = "einzelStunden")
    private String hours;

    @Column(name = "stundenGesamt")
    private int allHours;

    @Column(name = "abteilung")
    private String department;
}
