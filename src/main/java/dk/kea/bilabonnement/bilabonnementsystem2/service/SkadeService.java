package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Skadepris;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.SkadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeService {

    @Autowired
    private SkadeRepository skadeRepository;

    // Hent alle skadepriser
    public List<Skadepris> getSkadepriser() {
        return skadeRepository.findAllSkadepriser();
    }
}