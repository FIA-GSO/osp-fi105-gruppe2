package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.mapper.ApprenticeshipMapper;
import de.fi105.nachweiseBackend.model.ApprenticeshipData;
import de.fi105.nachweiseBackend.model.ApprenticeshipGet;
import de.fi105.nachweiseBackend.model.ApprenticeshipPatch;
import de.fi105.nachweiseBackend.repository.ApprenticeshipRepository;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        var entity = apprenticeshipMapper.fromData(input);

        var apprentice = personRepository.findByUsername(input.getApprenticeName())
                .orElseThrow(() -> new RuntimeException());
        var instructor = personRepository.findByUsername(input.getInstructorName())
                .orElseThrow(() -> new RuntimeException());

        entity.setApprenticeId(apprentice.getId());
        entity.setInstructorId(instructor.getId());

        var result = apprenticeshipRepository.save(entity);
        return apprenticeshipMapper.toGet(input, result.getId().intValue());
    }

    @Transactional
    public ApprenticeshipGet patchApprenticeship(ApprenticeshipPatch patch, int id) {
        var entitiy = apprenticeshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        entitiy.setEnd(ApprenticeshipMapper.dateToString(patch.getEnd()));

        var get = apprenticeshipMapper.fromEntity(apprenticeshipRepository.save(entitiy));
        get.setApprenticeName(personRepository.getReferenceById(entitiy.getApprenticeId()).getUsername());
        get.setInstructorName(personRepository.getReferenceById(entitiy.getInstructorId()).getUsername());
        return get;
    }

}
