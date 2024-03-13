package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<DayEntity, Integer> {

    @Query("select max(dayId) from DayEntity ")
    int getTopId();

    List<DayEntity> findAllByDayId(int dayId);

}
