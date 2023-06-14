package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    public List<Institution> getActiveInstitutions() {
        return institutionRepository.findAll().stream()
                .filter(Institution::isActive)
                .collect(Collectors.toList());
    }

    public Institution getInstitution(Long id) {
        return institutionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void saveInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    public void disableInstitution(Long id) {
        Institution institution = getInstitution(id);
        institution.setActive(false);
        institutionRepository.save(institution);
    }

    public void enableInstitution(Long id) {
        Institution institution = getInstitution(id);
        institution.setActive(true);
        institutionRepository.save(institution);
    }
}
