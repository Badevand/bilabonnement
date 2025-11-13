

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


    public void opretForhaandsaftale(String vognnummer, String afhentningssted) {


        Bil bil = bilService.getBilByVognnummer(vognnummer);

        // opret salgsaftale/forhaandsaftale
        Salgsaftale forhaandsaftale = new Salgsaftale(
                bil.getStelnummer(),
                bil.getMaerke(),
                0.0,
                "DKK",
                afhentningssted,
                true,
                null,
                null
        );

        salgsaftaleRepository.save(forhaandsaftale);
    }
}