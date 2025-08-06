package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Medarbejder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MedarbejderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper til at mappe database resultater til Medarbejder objekter
    private RowMapper<Medarbejder> medarbejderRowMapper = new RowMapper<Medarbejder>() {
        @Override
        public Medarbejder mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medarbejder medarbejder = new Medarbejder();
            medarbejder.setMedarbejderId(rs.getInt("medarbejder_id"));
            medarbejder.setBrugernavn(rs.getString("brugernavn"));
            medarbejder.setPassword(rs.getString("password"));
            medarbejder.setNavn(rs.getString("navn"));
            medarbejder.setEmail(rs.getString("email"));
            medarbejder.setRolle(rs.getString("rolle"));
            return medarbejder;
        }
    };

    // Find medarbejder baseret på brugernavn og password (til login)
    public Medarbejder findByBrugernavnAndPassword(String brugernavn, String password) {
        String sql = "SELECT * FROM medarbejdere WHERE brugernavn = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, medarbejderRowMapper, brugernavn, password);
        } catch (Exception e) {
            return null; // Returner null hvis ingen medarbejder findes
        }
    }
}