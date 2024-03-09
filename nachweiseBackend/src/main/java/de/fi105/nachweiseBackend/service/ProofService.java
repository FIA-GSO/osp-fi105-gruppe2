package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.DayEntity;
import de.fi105.nachweiseBackend.entity.EntryEntity;
import de.fi105.nachweiseBackend.entity.ProofEntity;
import de.fi105.nachweiseBackend.model.*;
import de.fi105.nachweiseBackend.repository.DayRepository;
import de.fi105.nachweiseBackend.repository.EntryRepository;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@AllArgsConstructor
@Component
public class ProofService {

    private final ProofRepository proofRepository;
    private final DayRepository dayRepository;
    private final EntryRepository entryRepository;
    private final AcknowledgementService acknowledgementService;

    @Transactional
    public ProofGet createProof(ProofPost proofPost, int userId, Date weekStartDate) {
        var proof = new ProofEntity()

                .setMonday(saveDay(proofPost.getMonday()))
                .setTuesday(saveDay(proofPost.getTuesday()))
                .setWednesday(saveDay(proofPost.getWednesday()))
                .setThursday(saveDay(proofPost.getThursday()))
                .setFriday(saveDay(proofPost.getFriday()))
                .setFriday(saveDay(proofPost.getSaturday()))

                .setMondayDepartment(proofPost.getMonday().getDepartment())
                .setTuesdayDepartment(proofPost.getTuesday().getDepartment())
                .setWednesdayDepartment(proofPost.getWednesday().getDepartment())
                .setThursdayDepartment(proofPost.getThursday().getDepartment())
                .setFridayDepartment(proofPost.getFriday().getDepartment())
                .setSaturdayDepartment(proofPost.getSaturday().getDepartment())

                .setApprenticeId(userId)
                .setRequested(proofPost.getRequested())
                .setWeekStartDate(weekStartDate);
        ProofEntity save = proofRepository.save(proof);
        return mapProof(save);
    }

    public ProofGet getProof(int proofId) {
        return mapProof(proofRepository.getReferenceById(proofId));
    }

    private ProofGet mapProof(ProofEntity entity) {
        return new ProofGet()
                .monday(mapDay(entity.getMonday()).department(entity.getMondayDepartment()))
                .tuesday(mapDay(entity.getTuesday()).department(entity.getTuesdayDepartment()))
                .wednesday(mapDay(entity.getWednesday()).department(entity.getWednesdayDepartment()))
                .thursday(mapDay(entity.getThursday()).department(entity.getThursdayDepartment()))
                .friday(mapDay(entity.getFriday()).department(entity.getFridayDepartment()))
                .saturday(mapDay(entity.getSaturday()).department(entity.getSaturdayDepartment()))
                .requested(entity.isRequested())
                .weekStartDate(String.valueOf(entity.getWeekStartDate()))
                .acknowledgement(acknowledgementService.getAcknowledgementsForProof(entity.getId()));
    }

    private DayGet mapDay(int dayId) {
        var get = new DayGet();
        var hours = 0;
        for (var e : dayRepository.findAllByDayId(dayId)) {
            EntryEntity entry = entryRepository.getReferenceById(e.getId());
            get.addEntriesItem(new DayEntryDataInner()
                    .hours(entry.getHours())
                    .workDone(entry.getWorkDone())
            );
            hours += entry.getHours();
        }
        get.setHours(hours);
        return get;
    }

    private int saveDay(DayPost dayPost) {
        var dayId = dayRepository.getTopId() + 10;
        dayPost.getEntries().stream().map(this::saveEntry).forEach(
                entryId -> dayRepository.save(
                        new DayEntity()
                                .setDayId(dayId)
                                .setId(entryId)
                )
        );
        return dayId;
    }

    private int saveEntry(DayEntryDataInner dayEntryData) {
        var entity = new EntryEntity()
                .setHours(dayEntryData.getHours())
                .setWorkDone(dayEntryData.getWorkDone());
        var save = entryRepository.save(entity);
        return save.getId();
    }
}
