package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "nachweis")
@Entity
public class ProofEntity {

    @Id
    @Column(name = "idNachweis")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auszubildenderId")
    private Integer apprenticeId;

    @Column(name = "startAusbildungswoche")
    private Date weekStartDate;

    @Column(name = "eintragMontagId")
    private int monday;

    @Column(name = "eintragDienstagId")
    private int tuesday;

    @Column(name = "eintragMittwochId")
    private int wednesday;

    @Column(name = "eintragDonnerstagId")
    private int thursday;

    @Column(name = "eintragFreitagId")
    private int friday;

    @Column(name = "eintragSamstagId")
    private int saturday;

    @Column(name = "abteilungMontag")
    private String mondayDepartment;

    @Column(name = "abteilungDienstag")
    private String tuesdayDepartment;

    @Column(name = "abteilungMittwoch")
    private String wednesdayDepartment;

    @Column(name = "abteilungDonnerstag")
    private String thursdayDepartment;

    @Column(name = "abteilungFreitag")
    private String fridayDepartment;

    @Column(name = "abteilungSamstag")
    private String saturdayDepartment;

    @Column(name = "zurQuittierung")
    private boolean requested;

}

