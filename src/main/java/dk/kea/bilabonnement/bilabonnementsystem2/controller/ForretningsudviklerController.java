package dk.kea.bilabonnement.bilabonnementsystem2.controller;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.service.BilService;
import java.util.List;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.ForretningsudviklerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;


@Controller
public class ForretningsudviklerController {

    @Autowired
    private ForretningsudviklerRepository forretningsudviklerRepository;

    @Autowired
    private BilService bilService;

    @GetMapping("/forretning")
    public String showForretningsudviklerPage(HttpSession session, Model model) {
        // Tjek om bruger er logget ind
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        // Hent data fra minimumskravene
        int antalUdlejedeBiler = forretningsudviklerRepository.getAntalUdlejedeBiler();
        double samletPrisUdlejedeBiler = forretningsudviklerRepository.getSamletPrisUdlejedeBiler();

        model.addAttribute("antalUdlejedeBiler", antalUdlejedeBiler);
        model.addAttribute("samletPrisUdlejedeBiler", samletPrisUdlejedeBiler);

        List<Bil> alleBiler = bilService.getAlleBiler();
        model.addAttribute("totalAntalBiler", alleBiler.size());

        return "forretningsudvikler";
    }
}