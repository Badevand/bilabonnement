package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Tilstandsrapport;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Lejeaftale;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.SkadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TilstandsrapportService {

    @Autowired
    private SkadeRepository skadeRepository;

    @Autowired
    private LejeaftaleService lejeaftaleService;

    @Autowired
    private BilService bilService;

    // Faste priser
    private static final int LAKFELT_PRIS = 1500;
    private static final int RIDSET_ALUFAELG_PRIS = 400;
    private static final int NY_FORRUDE_PRIS = 3000;
    private static final double KM_PRIS = 0.75;

    // Opret tilstandsrapport med beregninger
    public void opretTilstandsrapport(int lejeaftaleId, int lakfeltAntal, int ridsetAlufaelgAntal,
                                      int nyForrudeAntal, int overkoerteKm) {

        // Hent lejeaftale
        Lejeaftale lejeaftale = lejeaftaleService.getLejeaftaleById(lejeaftaleId);

        // Beregn skadepriser
        int lakfeltPris = lakfeltAntal * LAKFELT_PRIS;
        int ridsetAlufaelgPris = ridsetAlufaelgAntal * RIDSET_ALUFAELG_PRIS;
        int nyForrudePris = nyForrudeAntal * NY_FORRUDE_PRIS;
        double kmPris = overkoerteKm * KM_PRIS;

        // Samlet skadepris og total
        int totalSkadepris = lakfeltPris + ridsetAlufaelgPris + nyForrudePris;
        double totalKmPris = kmPris;

        // Opret tilstandsrapport
        Tilstandsrapport tilstandsrapport = new Tilstandsrapport();
        tilstandsrapport.setLejeaftaleId(lejeaftaleId);
        tilstandsrapport.setVognnummer(lejeaftale.getVognnummer());
        tilstandsrapport.setRapportDato(LocalDate.now());
        tilstandsrapport.setOverkoerteKm(overkoerteKm);
        tilstandsrapport.setKmPris(KM_PRIS);
        tilstandsrapport.setTotalSkadepris(totalSkadepris);
        tilstandsrapport.setTotalKmPris(totalKmPris);
        tilstandsrapport.setSlutopgoerelseSendt(false);

        // Gem tilstandsrapport
        skadeRepository.saveTilstandsrapport(tilstandsrapport);

        // Opdater bil status automatisk
        bilService.updateBilStatus(lejeaftale.getVognnummer(), "klar_til_afhentning_af_koeber");
    }

    // Send slutopgørelse
    public void sendSlutopgoerelse(int tilstandsrapportId, String email) {
        // For nu bare marker som sendt (senere kan du implementere email sending)
        // Du skulle have en metode i SkadeRepository til at opdatere slutopgoerelse_sendt flag
    }
}