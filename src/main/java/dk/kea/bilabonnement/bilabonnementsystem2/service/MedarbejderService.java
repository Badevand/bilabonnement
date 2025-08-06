package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Medarbejder;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.MedarbejderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedarbejderService {

    @Autowired
    private MedarbejderRepository medarbejderRepository;

    @Autowired
    private LejeaftaleService lejeaftaleService;

    // Håndter login og opdater udløbede lejeaftaler
    public Medarbejder login(String brugernavn, String password) {
        Medarbejder medarbejder = medarbejderRepository.findByBrugernavnAndPassword(brugernavn, password);

        if (medarbejder != null) {
            // Opdater udløbede lejeaftaler ved login
            lejeaftaleService.updateUdloebetLejeaftaler();
        }

        return medarbejder;
    }

    // Få redirect URL baseret på medarbejder rolle
    public String getRedirectUrlForRole(String rolle) {
        switch (rolle) {
            case "dataregistrering":
                return "redirect:/dataregistrering";
            case "skade_udbedring":
                return "redirect:/skade";
            case "forretningsudvikling":
                return "redirect:/forretning";
            default:
                return "redirect:/";
        }
    }
}