package dk.kea.bilabonnement.bilabonnementsystem2.controller;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Lejeaftale;
import dk.kea.bilabonnement.bilabonnementsystem2.service.BilService;
import dk.kea.bilabonnement.bilabonnementsystem2.service.LejeaftaleService;
import dk.kea.bilabonnement.bilabonnementsystem2.service.SalgsaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DataregistreringController {

    @Autowired
    private SalgsaftaleService salgsaftaleService;

    @Autowired
    private LejeaftaleService lejeaftaleService;

    @Autowired
    private BilService bilService;

    // Vis siden til dataregistrering
    @GetMapping("/dataregistrering")
    public String showDataregistreringPage(HttpSession session, Model model) {
        // Tjek om bruger er logget ind
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        // hent biler til dropdown
        List<Bil> ledigeBiler = bilService.getLedigeBiler(); // Til unlimited/limited lejeaftaler
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler(); // Til forhåndsaftaler
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // Opret lejeaftale (unlimited)
    @PostMapping("/dataregistrering")
    public String opretLejeaftale(@ModelAttribute Lejeaftale lejeaftale, Model model) {

        // Sæt status automatisk til aktiv
        lejeaftale.setStatus("aktiv");

        // Gem lejeaftalen og opdater bil status
        lejeaftaleService.opretLejeaftale(lejeaftale);

        // Vis bekræftelse
        model.addAttribute("success", "Lejeaftale oprettet!");

        // Hent opdaterede lister (bilen er nu udlejet)
        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // Opret lejeaftale (limited)
    @PostMapping("/dataregistrering-limited")
    public String opretLimitedLejeaftale(@RequestParam String vognnummer,
                                         @RequestParam LocalDate startDato,
                                         @RequestParam double maanedligPris,
                                         Model model) {

        // Opret limited lejeaftale med automatisk beregning
        lejeaftaleService.opretLimitedLejeaftale(vognnummer, startDato, maanedligPris);

        // Vis bekræftelse
        model.addAttribute("limitedSuccess", "Limited lejeaftale (150 dage) oprettet!");

        // Hent opdaterede lister (bilen er nu udlejet)
        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // opret forhåndsaftale
    @PostMapping("/opret-forhaandsaftale")
    public String opretForhaandsaftale(@RequestParam String vognnummer,
                                       @RequestParam String afhentningssted,
                                       Model model) {

        // Opret forhåndsaftale
        salgsaftaleService.opretForhaandsaftale(vognnummer, afhentningssted);

        // Hent bil info til success besked
        Bil bil = bilService.getBilByVognnummer(vognnummer);

        // Vis bekræftelse
        model.addAttribute("forhaandsaftaleSuccess", "Forhåndsaftale oprettet for " + bil.getMaerke() + " " + bil.getModel());

        // Hent data igen til siden
        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // Fleet
    @PostMapping("/traek-fleet-liste")
    public String traekFleetListe(Model model) {

        // Hent biler til udlevering (ledige biler med aktive lejeaftaler)
        List<Bil> udleveringsliste = bilService.getBilerTilUdlevering();

        // Tilføj til model så listen vises
        model.addAttribute("udleveringsliste", udleveringsliste);
        model.addAttribute("fleetListeHentet", true);

        // Hent standard data til siden
        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }
}