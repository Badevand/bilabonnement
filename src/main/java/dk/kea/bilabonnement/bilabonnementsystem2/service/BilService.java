

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
        List<Bil> alleBiler = new ArrayList<>();


        alleBiler.addAll(getLedigeBiler());
        alleBiler.addAll(getUdlejedeBiler());
        alleBiler.addAll(getBilerMedSkadeStatus());

        return alleBiler;
    }

    public List<Bil> getLedigeBiler() {
        return bilRepository.findAllLedigeBiler();
    }

    public List<Bil> getUdlejedeBiler() {
        return bilRepository.findUdlejedeBiler();
    }

    public Bil getBilByVognnummer(String vognnummer) {
        return bilRepository.findByVognnummer(vognnummer);
    }

    public void updateBilStatus(String vognnummer, String nyStatus) {
        bilRepository.updateBilStatus(vognnummer, nyStatus);
    }




    // Fleet
    public List<Bil> getBilerTilUdlevering() {
        return bilRepository.findBilerTilUdlevering();
    }

    public List<Bil> getBilerMedSkadeStatus() {
        return bilRepository.findBilerMedSkadeStatus();
    }


}