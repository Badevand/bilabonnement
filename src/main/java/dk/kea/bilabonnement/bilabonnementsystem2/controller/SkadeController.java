package dk.kea.bilabonnement.bilabonnementsystem2.controller;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Skadepris;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Lejeaftale;
import dk.kea.bilabonnement.bilabonnementsystem2.service.BilService;
import dk.kea.bilabonnement.bilabonnementsystem2.service.LejeaftaleService;
import dk.kea.bilabonnement.bilabonnementsystem2.service.SkadeService;
import dk.kea.bilabonnement.bilabonnementsystem2.service.TilstandsrapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SkadeController {

    @Autowired
    private BilService bilService;

    @Autowired
    private SkadeService skadeService;

    @Autowired
    private TilstandsrapportService tilstandsrapportService;

    @Autowired
    private LejeaftaleService lejeaftaleService;

    // Vis siden til skade & udbedring
    @GetMapping("/skade")
    public String showSkadePage(HttpSession session, Model model) {
        // Tjek om bruger er logget ind
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        // Hent biler med skade status
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);

        // Hent skadetyper og priser
        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);

        // Hent afsluttede lejeaftaler til tilstandsrapport funktionalitet
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }

    // Håndter oprettelse af tilstandsrapport
    @PostMapping("/opret-tilstandsrapport")
    public String opretTilstandsrapport(@RequestParam int lejeaftaleId,
                                        @RequestParam int lakfeltAntal,
                                        @RequestParam int ridsetAlufaelgAntal,
                                        @RequestParam int nyForrudeAntal,
                                        @RequestParam int overkoerteKm,
                                        Model model) {

        // Opret tilstandsrapport med automatisk beregning
        tilstandsrapportService.opretTilstandsrapport(lejeaftaleId, lakfeltAntal, ridsetAlufaelgAntal, nyForrudeAntal, overkoerteKm);

        // Vis bekræftelse
        model.addAttribute("tilstandsrapportSuccess", "Tilstandsrapport oprettet! Bil status ændret til 'klar til afhentning af køber'.");

        // Hent data til siden
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);
        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }

    // Send slutopgørelse
    @PostMapping("/send-slutopgoerelse")
    public String sendSlutopgoerelse(@RequestParam int tilstandsrapportId,
                                     @RequestParam String email,
                                     Model model) {

        // Send slutopgørelse
        tilstandsrapportService.sendSlutopgoerelse(tilstandsrapportId, email);

        // Vis bekræftelse
        model.addAttribute("slutopgoerelseSendt", "Slutopgørelse sendt til " + email);

        // Hent data til siden
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);
        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }
}