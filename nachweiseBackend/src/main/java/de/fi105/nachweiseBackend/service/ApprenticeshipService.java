package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.ApprenticeshipEntity;
import de.fi105.nachweiseBackend.mapper.ApprenticeshipMapper;
import de.fi105.nachweiseBackend.model.ApprenticeshipData;
import de.fi105.nachweiseBackend.model.ApprenticeshipGet;
import de.fi105.nachweiseBackend.model.ApprenticeshipPatch;
import de.fi105.nachweiseBackend.repository.ApprenticeshipRepository;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ApprenticeshipService {

    private final ApprenticeshipRepository apprenticeshipRepository;
    private final PersonRepository personRepository;
    private final ApprenticeshipMapper apprenticeshipMapper;

    public ApprenticeshipService(ApprenticeshipRepository apprenticeshipRepository, PersonRepository personRepository, ApprenticeshipMapper apprenticeshipMapper) {
        this.apprenticeshipRepository = apprenticeshipRepository;
        this.personRepository = personRepository;
        this.apprenticeshipMapper = apprenticeshipMapper;
    }

    @Transactional
    public ApprenticeshipGet postApprenticeship(ApprenticeshipData input) {
        var apprentice = personRepository.findByUsername(input.getApprenticeName())
                .orElseThrow(RuntimeException::new);
        if (conflicts(input, apprentice.getId())) {
            throw new RuntimeException();
        }
        var entity = apprenticeshipMapper.fromData(input);
        var instructor = personRepository.findByUsername(input.getInstructorName())
                .orElseThrow(RuntimeException::new);

        entity.setApprenticeId(apprentice.getId());
        entity.setInstructorId(instructor.getId());

        var result = apprenticeshipRepository.save(entity);
        return apprenticeshipMapper.toGet(input, result.getId());
    }

    @Transactional
    public ApprenticeshipGet patchApprenticeship(ApprenticeshipPatch patch, int id) {
        var entity = apprenticeshipRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        if (getActiveApprenticeShip(entity.getApprenticeId(), LocalDate.parse(patch.getEnd())).isPresent()) {
            throw new RuntimeException();
        }

        entity.setEnd(ApprenticeshipMapper.dateToString(patch.getEnd()));

        var get = apprenticeshipMapper.fromEntity(apprenticeshipRepository.save(entity));
        get.setApprenticeName(personRepository.getReferenceById(entity.getApprenticeId()).getUsername());
        get.setInstructorName(personRepository.getReferenceById(entity.getInstructorId()).getUsername());
        return get;
    }

    public Optional<ApprenticeshipEntity> getActiveApprenticeShip(int userId, LocalDate date) {
        List<ApprenticeshipEntity> allByApprenticeId = apprenticeshipRepository.findAllByApprenticeId(userId);
        for (var apprenticeship : allByApprenticeId) {
            var start = apprenticeship.getStart().toLocalDate();
            var end = apprenticeship.getEnd().toLocalDate();
            if (
                (start.isBefore(date) || start.equals(date)) &&
                (end.isAfter(date) || end.equals(date))
            ) {
                return Optional.of(apprenticeship);
            }
        }
        return Optional.empty();
    }

    public List<String> getApprentices(int instructorId) {
        return apprenticeshipRepository.findAllByInstructorId(instructorId).stream()
                .map(ApprenticeshipEntity::getApprenticeId)
                .map(id -> personRepository.getReferenceById(id).getUsername())
                .toList();
    }

    private boolean conflicts(ApprenticeshipData input, int apprenticeId) {
        var inStart = ApprenticeshipMapper.dateToString(input.getStart()).toLocalDate();
        var inEnd = ApprenticeshipMapper.dateToString(input.getEnd()).toLocalDate();

        if (getActiveApprenticeShip(apprenticeId, inStart).isPresent()) {
            return true;
        }
        if (getActiveApprenticeShip(apprenticeId, inEnd).isPresent()) {
            return true;
        }

        List<ApprenticeshipEntity> allByApprenticeId = apprenticeshipRepository.findAllByApprenticeId(apprenticeId);
        for (var apprenticeship : allByApprenticeId) {
            var start = apprenticeship.getStart().toLocalDate();
            var end = apprenticeship.getEnd().toLocalDate();
            if (start.isAfter(apprenticeship.getStart().toLocalDate()) && end.isBefore(apprenticeship.getEnd().toLocalDate())) {
                return true;
            }
        }
        return false;
    }

}
