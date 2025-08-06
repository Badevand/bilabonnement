package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BilService implements BaseService<Bil> {

    @Autowired
    private BilRepository bilRepository;

    @Override
    public List<Bil> findAll() {
        return getAlleBiler();
    }

    // Hent alle ledige biler
    public List<Bil> getLedigeBiler() {
        return bilRepository.findAllLedigeBiler();
    }

    // Hent alle udlejede biler
    public List<Bil> getUdlejedeBiler() {
        return bilRepository.findUdlejedeBiler();
    }

    // Find bil baseret på vognnummer
    public Bil getBilByVognnummer(String vognnummer) {
        return bilRepository.findByVognnummer(vognnummer);
    }

    // Opdater bil status
    public void updateBilStatus(String vognnummer, String nyStatus) {
        bilRepository.updateBilStatus(vognnummer, nyStatus);
    }

    // Hent biler til fleet udlevering
    public List<Bil> getBilerTilUdlevering() {
        return bilRepository.findBilerTilUdlevering();
    }

    public List<Bil> getBilerMedSkadeStatus() {
        return bilRepository.findBilerMedSkadeStatus();
    }

    public List<Bil> getAlleBiler() {
        List<Bil> alleBiler = new ArrayList<>();

        // Saml biler fra forskellige sources med ArrayList
        alleBiler.addAll(getLedigeBiler());        // Ledige biler
        alleBiler.addAll(getUdlejedeBiler());      // Udlejede biler
        alleBiler.addAll(getBilerMedSkadeStatus()); // Skadede biler

        return alleBiler;
    }







}