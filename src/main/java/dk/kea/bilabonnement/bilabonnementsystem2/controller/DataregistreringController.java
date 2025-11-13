

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


    @GetMapping("/dataregistrering")
    public String showDataregistreringPage(HttpSession session, Model model) {


        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        List<Bil> ledigeBiler = bilService.getLedigeBiler(); // til unlimited/limited lejeaftaler
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler(); // til forhåndsaftaler
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // opret lejeaftale (unlimited)
    @PostMapping("/dataregistrering")
    public String opretLejeaftale(@ModelAttribute Lejeaftale lejeaftale, Model model) {

        lejeaftale.setStatus("aktiv");

        lejeaftaleService.opretLejeaftale(lejeaftale);

        model.addAttribute("success", "Lejeaftale oprettet!");

        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // opret lejeaftale (limited)
    @PostMapping("/dataregistrering-limited")
    public String opretLimitedLejeaftale(@RequestParam String vognnummer,
                                         @RequestParam LocalDate startDato,
                                         @RequestParam double maanedligPris,
                                         Model model) {


        lejeaftaleService.opretLimitedLejeaftale(vognnummer, startDato, maanedligPris);

        model.addAttribute("limitedSuccess", "Limited lejeaftale (150 dage) oprettet!");


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


        salgsaftaleService.opretForhaandsaftale(vognnummer, afhentningssted);


        Bil bil = bilService.getBilByVognnummer(vognnummer);
        model.addAttribute("forhaandsaftaleSuccess", "Forhåndsaftale oprettet for " + bil.getMaerke() + " " + bil.getModel());


        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }

    // fleet
    @PostMapping("/traek-fleet-liste")
    public String traekFleetListe(Model model) {

        // hent biler til udlevering (ledige biler med aktive lejeaftaler)
        List<Bil> udleveringsliste = bilService.getBilerTilUdlevering();

        // tilføj til model så listen vises
        model.addAttribute("udleveringsliste", udleveringsliste);
        model.addAttribute("fleetListeHentet", true);

        // hent standard data til siden
        List<Bil> ledigeBiler = bilService.getLedigeBiler();
        List<Bil> udlejedeBiler = bilService.getUdlejedeBiler();
        model.addAttribute("ledigeBiler", ledigeBiler);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("lejeaftale", new Lejeaftale());

        return "dataregistrering";
    }
}