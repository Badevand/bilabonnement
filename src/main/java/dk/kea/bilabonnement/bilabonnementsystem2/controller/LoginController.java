package dk.kea.bilabonnement.bilabonnementsystem2.controller;


import dk.kea.bilabonnement.bilabonnementsystem2.model.Medarbejder;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.LejeaftaleRepository;
import dk.kea.bilabonnement.bilabonnementsystem2.service.MedarbejderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private MedarbejderService medarbejderService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Vis login siden
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // returnerer login.html template
    }

    // Håndter login forsøg
    @PostMapping("/login")
    public String handleLogin(@RequestParam String brugernavn,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        // Håndter login
        Medarbejder medarbejder = medarbejderService.login(brugernavn, password);

        if (medarbejder != null) {
            // Login success - gem bruger i session
            session.setAttribute("loggedInUser", medarbejder);

            // Redirect baseret på rolle
            return medarbejderService.getRedirectUrlForRole(medarbejder.getRolle());
        } else {
            // Login failed
            model.addAttribute("error", "Forkert brugernavn eller password");
            return "login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}