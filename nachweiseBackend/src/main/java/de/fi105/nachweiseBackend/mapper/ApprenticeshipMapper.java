package de.fi105.nachweiseBackend.mapper;

import de.fi105.nachweiseBackend.entity.ApprenticeshipEntity;
import de.fi105.nachweiseBackend.model.ApprenticeshipData;
import de.fi105.nachweiseBackend.model.ApprenticeshipGet;
import org.mapstruct.Mapper;

import java.sql.Date;

@Mapper
public interface ApprenticeshipMapper {

    ApprenticeshipEntity fromData(ApprenticeshipData apprenticeshipData);

    ApprenticeshipGet toGet(ApprenticeshipData entity, int id);

    ApprenticeshipGet fromEntity(ApprenticeshipEntity entity);

    static Date dateToString(String value) {
        return Date.valueOf(value);
    }

    static String stringToDate(Date value) {
        return value.toString();
    }
}
