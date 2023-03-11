package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public List<Institution> getInstitutions(){
        return institutionRepository.findAll();
    }
}
