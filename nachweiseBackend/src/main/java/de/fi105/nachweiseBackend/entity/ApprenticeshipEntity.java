package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "ausbildung")
@Entity
public class ApprenticeshipEntity {

    @Id
    @Column(name = "idAusbildung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auszubildenderId")
    private Integer apprenticeId;

    @Column(name = "ausbilderId")
    private Integer instructorId;

    @Column(name = "beruf")
    private String profession;

    @Column(name = "startZeitpunkt")
    private Date start;

    @Column(name = "endZeitpunkt")
    private Date end;
}
