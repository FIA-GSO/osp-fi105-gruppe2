package de.fi105.nachweiseBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "tag")
@Entity
public class DayEntity {

    @Id
    @Column(name = "idEintrag")
    private Integer id;

    @Column(name = "idTag")
    private Integer dayId;

}
