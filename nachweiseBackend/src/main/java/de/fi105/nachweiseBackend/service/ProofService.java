package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.DayEntity;
import de.fi105.nachweiseBackend.entity.EntryEntity;
import de.fi105.nachweiseBackend.entity.ProofEntity;
import de.fi105.nachweiseBackend.model.*;
import de.fi105.nachweiseBackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Component
public class ProofService {

    private final ProofRepository proofRepository;
    private final DayRepository dayRepository;
    private final EntryRepository entryRepository;
    private final AcknowledgementService acknowledgementService;
    private final ApprenticeshipRepository apprenticeshipRepository;

    @Transactional
    public ProofGet createProof(ProofPost proofPost, int apprenticeship) {
        var proofCount = proofRepository.findAllByApprenticeshipId(apprenticeship).size();
        Date date = apprenticeshipRepository.getReferenceById(apprenticeship).getStart();
        for (int i = 0; i < proofCount; i++) {
            date = Date.valueOf(date.toLocalDate().plusDays(7));
        }
        var proof = new ProofEntity()
                .setMonday(saveDay(proofPost.getMonday()))
                .setTuesday(saveDay(proofPost.getTuesday()))
                .setWednesday(saveDay(proofPost.getWednesday()))
                .setThursday(saveDay(proofPost.getThursday()))
                .setFriday(saveDay(proofPost.getFriday()))
                .setSaturday(saveDay(proofPost.getSaturday()))

                .setMondayDepartment(proofPost.getMonday().getDepartment())
                .setTuesdayDepartment(proofPost.getTuesday().getDepartment())
                .setWednesdayDepartment(proofPost.getWednesday().getDepartment())
                .setThursdayDepartment(proofPost.getThursday().getDepartment())
                .setFridayDepartment(proofPost.getFriday().getDepartment())
                .setSaturdayDepartment(proofPost.getSaturday().getDepartment())

                .setApprenticeshipId(apprenticeship)
                .setRequested(proofPost.getRequested())
                .setReviewed(false)
                .setWeekStartDate(date);
        ProofEntity save = proofRepository.save(proof);
        return mapProof(save);
    }

    @Transactional
    public ProofGet patchProof(ProofPatch proofPatch, int proofId) {
        var entity = proofRepository.getReferenceById(proofId);
        DayPatch monday = proofPatch.getMonday();
        DayPatch thursday = proofPatch.getThursday();
        DayPatch tuesday = proofPatch.getTuesday();
        DayPatch wednesday = proofPatch.getWednesday();
        DayPatch friday = proofPatch.getFriday();
        DayPatch saturday = proofPatch.getSaturday();

        if (monday != null) {
            if (monday.getDepartment() != null) {
                entity.setMondayDepartment(monday.getDepartment());
            }
            changeDay(monday, entity.getMonday());
        }
        if (tuesday != null) {
            if (tuesday.getDepartment() != null) {
                entity.setTuesdayDepartment(tuesday.getDepartment());
            }
            changeDay(tuesday, entity.getTuesday());
        }
        if (wednesday != null) {
            if (wednesday.getDepartment() != null) {
                entity.setWednesdayDepartment(wednesday.getDepartment());
            }
            changeDay(wednesday, entity.getWednesday());
        }
        if (thursday != null) {
            if (thursday.getDepartment() != null) {
                entity.setThursdayDepartment(thursday.getDepartment());
            }
            changeDay(thursday, entity.getThursday());
        }
        if (friday != null) {
            if (friday.getDepartment() != null) {
                entity.setFridayDepartment(friday.getDepartment());
            }
            changeDay(friday, entity.getFriday());
        }
        if (saturday != null) {
            if (saturday.getDepartment() != null) {
                entity.setSaturdayDepartment(saturday.getDepartment());
            }
            changeDay(saturday, entity.getSaturday());
        }

        entity.setRequested(proofPatch.getRequested());
        entity.setReviewed(false);

        return mapProof(proofRepository.save(entity));
    }

    private void changeDay(DayPatch day, int dayId) {
        List<DayEntity> allByDayId = dayRepository.findAllByDayId(dayId);
        dayRepository.deleteAll(allByDayId);
        allByDayId.forEach(e -> entryRepository.deleteById(e.getId()));
        saveEntries(day.getEntries(), dayId);
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
        int dayId = 0;
        try {
            dayId = dayRepository.getTopId() + 1;
        } catch (Exception ignored){}
        saveEntries(dayPost.getEntries(), dayId);
        return dayId;
    }

    private void saveEntries(List<DayEntryDataInner> entries, int day) {
        entries.stream().map(this::saveEntry).forEach(
                entryId -> dayRepository.save(
                        new DayEntity()
                                .setDayId(day)
                                .setId(entryId)
                )
        );
    }

    private int saveEntry(DayEntryDataInner dayEntryData) {
        var entity = new EntryEntity()
                .setHours(dayEntryData.getHours())
                .setWorkDone(dayEntryData.getWorkDone());
        var save = entryRepository.save(entity);
        return save.getId();
    }
}
