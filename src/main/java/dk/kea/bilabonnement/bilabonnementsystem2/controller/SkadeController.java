

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


    @GetMapping("/skade")
    public String showSkadePage(HttpSession session, Model model) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        // bruges ikke
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);

        // skadetyper og priser
        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);

        // afsluttede lejeaftaler til tilstandsrapport
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }

    // håndter oprettelse af tilstandsrapport
    @PostMapping("/opret-tilstandsrapport")
    public String opretTilstandsrapport(@RequestParam int lejeaftaleId,
                                        @RequestParam int lakfeltAntal,
                                        @RequestParam int ridsetAlufaelgAntal,
                                        @RequestParam int nyForrudeAntal,
                                        @RequestParam int overkoerteKm,
                                        Model model) {

        // opret tilstandsrapport med automatisk beregning
        tilstandsrapportService.opretTilstandsrapport(lejeaftaleId, lakfeltAntal, ridsetAlufaelgAntal, nyForrudeAntal, overkoerteKm);

        model.addAttribute("tilstandsrapportSuccess", "Tilstandsrapport oprettet!" +
                "Bil status ændret til 'klar til afhentning af køber'.");


        // bruges ikke
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);


        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }

    // send slutopgørelse
    @PostMapping("/send-slutopgoerelse")
    public String sendSlutopgoerelse(@RequestParam int tilstandsrapportId,
                                     @RequestParam String email,
                                     Model model) {

        // send slutopgørelse
        tilstandsrapportService.sendSlutopgoerelse(tilstandsrapportId, email);

        model.addAttribute("slutopgoerelseSendt", "Slutopgørelse sendt til " + email);


        // bruges ikke
        List<Bil> skadedeBiler = bilService.getBilerMedSkadeStatus();
        model.addAttribute("skadedeBiler", skadedeBiler);


        List<Skadepris> skadepriser = skadeService.getSkadepriser();
        model.addAttribute("skadepriser", skadepriser);
        List<Lejeaftale> afsluttedeLejeaftaler = lejeaftaleService.getAfsluttedeLejeaftaler();
        model.addAttribute("afsluttedeLejeaftaler", afsluttedeLejeaftaler);

        return "skade";
    }
}