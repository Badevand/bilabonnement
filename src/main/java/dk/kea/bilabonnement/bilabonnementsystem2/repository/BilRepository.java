package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BilRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper til at mappe database resultater til Bil objekter
    private RowMapper<Bil> bilRowMapper = new RowMapper<Bil>() {
        @Override
        public Bil mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bil bil = new Bil();
            bil.setVognnummer(rs.getString("vognnummer"));
            bil.setStelnummer(rs.getString("stelnummer"));
            bil.setMaerke(rs.getString("maerke"));
            bil.setModel(rs.getString("model"));
            bil.setUdstyrsniveau(rs.getString("udstyrsniveau"));
            bil.setStaalpris(rs.getDouble("staalpris"));
            bil.setRegAfgift(rs.getDouble("reg_afgift"));
            bil.setCo2Udledning(rs.getInt("co2_udledning"));
            bil.setStatus(rs.getString("status"));
            bil.setKmVedStart(rs.getInt("km_ved_start"));
            return bil;
        }
    };

    // Find alle ledige biler (til dropdown når man opretter lejeaftale)
    public List<Bil> findAllLedigeBiler() {
        String sql = "SELECT * FROM biler WHERE status = 'ledig'";
        return jdbcTemplate.query(sql, bilRowMapper);
    }

    // Find biler med skade status
    public List<Bil> findBilerMedSkadeStatus() {
        String sql = "SELECT * FROM biler WHERE status = 'skade'";
        return jdbcTemplate.query(sql, bilRowMapper);
    }

    // Opdater bil status
    public void updateBilStatus(String vognnummer, String nyStatus) {
        String sql = "UPDATE biler SET status = ? WHERE vognnummer = ?";
        jdbcTemplate.update(sql, nyStatus, vognnummer);
    }

    public Bil findByVognnummer(String vognnummer) {
        String sql = "SELECT * FROM biler WHERE vognnummer = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Bil.class), vognnummer);
    }

    public List<Bil> findBilerTilUdlevering() {
        String sql = """
        SELECT DISTINCT b.* 
        FROM biler b 
        JOIN lejeaftaler l ON b.vognnummer = l.vognnummer 
        WHERE l.status = 'aktiv'
        ORDER BY l.start_dato
        LIMIT 5
        """;

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
        } catch (Exception e) {
            // Hvis der er fejl, returner bare alle ledige biler
            return findAllLedigeBiler();
        }
    }

    public List<Bil> findUdlejedeBiler() {
        String sql = "SELECT * FROM biler WHERE status = 'udlejet' ORDER BY vognnummer";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }

}