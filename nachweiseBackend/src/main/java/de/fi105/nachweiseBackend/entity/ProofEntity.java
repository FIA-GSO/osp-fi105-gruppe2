package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "Nachweis")
@Entity
public class ProofEntity {

    @Id
    @Column(name = "idNachweis")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auszubildenderId")
    private Long apprenticeId;

    @Column(name = "ggfAusbildungsAbteilung")
    private String info;

    @Column(name = "startAusbildungswoche")
    private Date weekStartDate;

    @OneToOne
    @JoinColumn(name = "eintragMontagId", referencedColumnName = "idEintrag")
    private EntryEntity monday;

    @OneToOne
    @JoinColumn(name = "eintragDienstagId", referencedColumnName = "idEintrag")
    private EntryEntity tuesday;

    @OneToOne
    @JoinColumn(name = "eintragMittwochId", referencedColumnName = "idEintrag")
    private EntryEntity wednesday;

    @OneToOne
    @JoinColumn(name = "eintragDonnerstagId", referencedColumnName = "idEintrag")
    private EntryEntity thursday;

    @OneToOne
    @JoinColumn(name = "eintragFreitagId", referencedColumnName = "idEintrag")
    private EntryEntity friday;

}

