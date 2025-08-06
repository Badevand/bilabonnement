// LejeaftaleRepository.java - placeres i din repository mappe
package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Lejeaftale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LejeaftaleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper til at mappe database resultater til Lejeaftale objekter
    private RowMapper<Lejeaftale> lejeaftaleRowMapper = new RowMapper<Lejeaftale>() {
        @Override
        public Lejeaftale mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lejeaftale lejeaftale = new Lejeaftale();
            lejeaftale.setLejeaftaleId(rs.getInt("lejeaftale_id"));
            lejeaftale.setVognnummer(rs.getString("vognnummer"));
            lejeaftale.setStartDato(rs.getObject("start_dato", LocalDate.class));
            lejeaftale.setSlutDato(rs.getObject("slut_dato", LocalDate.class));
            lejeaftale.setMaanedligPris(rs.getDouble("maanedlig_pris"));
            lejeaftale.setTotalPris(rs.getDouble("total_pris"));
            lejeaftale.setStatus(rs.getString("status"));
            lejeaftale.setOprettetDato(rs.getObject("oprettet_dato", LocalDateTime.class));
            return lejeaftale;
        }
    };

    // Opret ny lejeaftale
    public void save(Lejeaftale lejeaftale) {
        String sql = "INSERT INTO lejeaftaler (vognnummer, start_dato, slut_dato, maanedlig_pris, total_pris, status) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                lejeaftale.getVognnummer(),
                lejeaftale.getStartDato(),
                lejeaftale.getSlutDato(),
                lejeaftale.getMaanedligPris(),
                lejeaftale.getTotalPris(),
                lejeaftale.getStatus()
        );
    }

    // Find afsluttede lejeaftaler
    public List<Lejeaftale> findAfsluttedeLejeaftaler() {
        String sql = "SELECT * FROM lejeaftaler WHERE status = 'afsluttet' ORDER BY slut_dato DESC";
        return jdbcTemplate.query(sql, lejeaftaleRowMapper);
    }

    // Find lejeaftale by ID
    public Lejeaftale findById(int lejeaftaleId) {
        String sql = "SELECT * FROM lejeaftaler WHERE lejeaftale_id = ?";
        return jdbcTemplate.queryForObject(sql, lejeaftaleRowMapper, lejeaftaleId);
    }

    // Opdater udløbede lejeaftaler automatisk
    public void updateUdloebetLejeaftaler() {
        String sql = "UPDATE lejeaftaler SET status = 'afsluttet' WHERE slut_dato < CURDATE() AND status = 'aktiv'";
        jdbcTemplate.update(sql);
    }
}