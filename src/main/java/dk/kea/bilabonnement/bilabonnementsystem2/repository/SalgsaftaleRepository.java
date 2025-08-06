package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Salgsaftale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SalgsaftaleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Salgsaftale salgsaftale) {
        String sql = "INSERT INTO salgsaftaler (stelnummer, maerke, koebspris, valuta, afhentningssted, er_forhaandsaftale, faktura_email, reg_attest_adresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                salgsaftale.getStelnummer(),
                salgsaftale.getMaerke(),
                salgsaftale.getKoebspris(),
                salgsaftale.getValuta(),
                salgsaftale.getAfhentningssted(),
                salgsaftale.isErForhaandsaftale(),
                salgsaftale.getFakturaEmail(),
                salgsaftale.getRegAttestAdresse()
        );
    }
}