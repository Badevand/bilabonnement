

package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ForretningsudviklerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int getAntalUdlejedeBiler() {
        String sql = """
        SELECT COUNT(*) 
        FROM biler 
        WHERE status = 'udlejet'
        """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public double getSamletPrisUdlejedeBiler() {
        String sql = """
        SELECT SUM(l.total_pris) 
        FROM lejeaftaler l
        JOIN biler b ON l.vognnummer = b.vognnummer
        WHERE b.status = 'udlejet' 
        AND l.status = 'aktiv'
        """;
        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        return result != null ? result : 0.0;
    }

}