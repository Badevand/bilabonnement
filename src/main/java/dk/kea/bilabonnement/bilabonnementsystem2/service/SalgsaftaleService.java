package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Salgsaftale;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.SalgsaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalgsaftaleService {

    @Autowired
    private SalgsaftaleRepository salgsaftaleRepository;

    @Autowired
    private BilService bilService;

    // Opret forhåndsaftale
    public void opretForhaandsaftale(String vognnummer, String afhentningssted) {
        // Hent bil-detaljer baseret på vognnummer
        Bil bil = bilService.getBilByVognnummer(vognnummer);

        // Opret salgsaftale (forhåndsaftale uden fradrag - det beregnes ved tilstandsrapport)
        Salgsaftale forhaandsaftale = new Salgsaftale(
                bil.getStelnummer(),
                bil.getMaerke(),
                0.0, // Ingen fradrag endnu - beregnes ved tilstandsrapport
                "DKK",
                afhentningssted,
                true, // er_forhaandsaftale = true
                null, // faktura_email
                null  // reg_attest_adresse
        );

        // Gem forhåndsaftalen
        salgsaftaleRepository.save(forhaandsaftale);
    }
}