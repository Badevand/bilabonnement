package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Lejeaftale;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.LejeaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LejeaftaleService {

    @Autowired
    private LejeaftaleRepository lejeaftaleRepository;

    @Autowired
    private BilService bilService;

    // Gem lejeaftale og opdater bil status
    public void opretLejeaftale(Lejeaftale lejeaftale) {
        lejeaftaleRepository.save(lejeaftale);
        bilService.updateBilStatus(lejeaftale.getVognnummer(), "udlejet");
    }

    // Opret limited lejeaftale med beregninger
    public void opretLimitedLejeaftale(String vognnummer, LocalDate startDato, double maanedligPris) {
        LocalDate slutDato = startDato.plusDays(150);
        double totalPris = maanedligPris * 5;

        Lejeaftale limitedLejeaftale = new Lejeaftale(vognnummer, startDato, slutDato, maanedligPris, totalPris, "aktiv");

        lejeaftaleRepository.save(limitedLejeaftale);
        bilService.updateBilStatus(vognnummer, "udlejet");
    }

    // Hent afsluttede lejeaftaler
    public List<Lejeaftale> getAfsluttedeLejeaftaler() {
        return lejeaftaleRepository.findAfsluttedeLejeaftaler();
    }

    // Find lejeaftale baseret på ID
    public Lejeaftale getLejeaftaleById(int lejeaftaleId) {
        return lejeaftaleRepository.findById(lejeaftaleId);
    }

    // Opdater udløbede lejeaftaler
    public void updateUdloebetLejeaftaler() {
        lejeaftaleRepository.updateUdloebetLejeaftaler();
    }
}