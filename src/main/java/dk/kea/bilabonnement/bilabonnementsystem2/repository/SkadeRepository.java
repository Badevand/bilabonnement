

package dk.kea.bilabonnement.bilabonnementsystem2.repository;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Skadepris;
import dk.kea.bilabonnement.bilabonnementsystem2.model.Tilstandsrapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SkadeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


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


    private RowMapper<Skadepris> skadeprisRowMapper = new RowMapper<Skadepris>() {
        @Override
        public Skadepris mapRow(ResultSet rs, int rowNum) throws SQLException {
            Skadepris skadepris = new Skadepris();
            skadepris.setSkadetypeId(rs.getInt("skadetype_id"));
            skadepris.setSkadetype(rs.getString("skadetype"));
            skadepris.setPris(rs.getInt("pris"));
            return skadepris;
        }
    };


    private RowMapper<Tilstandsrapport> tilstandsrapportRowMapper = new RowMapper<Tilstandsrapport>() {
        @Override
        public Tilstandsrapport mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tilstandsrapport tilstandsrapport = new Tilstandsrapport();
            tilstandsrapport.setTilstandsrapportId(rs.getInt("tilstandsrapport_id"));
            tilstandsrapport.setLejeaftaleId(rs.getInt("lejeaftale_id"));
            tilstandsrapport.setVognnummer(rs.getString("vognnummer"));
            tilstandsrapport.setRapportDato(rs.getObject("rapport_dato", LocalDate.class));
            tilstandsrapport.setOverkoerteKm(rs.getInt("overkoerte_km"));
            tilstandsrapport.setKmPris(rs.getDouble("km_pris"));
            tilstandsrapport.setTotalSkadepris(rs.getInt("total_skadepris"));
            tilstandsrapport.setTotalKmPris(rs.getDouble("total_km_pris"));
            tilstandsrapport.setSlutopgoerelseSendt(rs.getBoolean("slutopgoerelse_sendt"));
            return tilstandsrapport;
        }
    };

    public List<Skadepris> findAllSkadepriser() {
        String sql = """
        SELECT * 
        FROM skadepriser
        """;
        return jdbcTemplate.query(sql, skadeprisRowMapper);
    }


    public int saveTilstandsrapport(Tilstandsrapport tilstandsrapport) {
        String sql = """
        INSERT INTO tilstandsrapporter (lejeaftale_id, vognnummer, rapport_dato, overkoerte_km, km_pris, total_skadepris, total_km_pris, slutopgoerelse_sendt) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                tilstandsrapport.getLejeaftaleId(),
                tilstandsrapport.getVognnummer(),
                tilstandsrapport.getRapportDato(),
                tilstandsrapport.getOverkoerteKm(),
                tilstandsrapport.getKmPris(),
                tilstandsrapport.getTotalSkadepris(),
                tilstandsrapport.getTotalKmPris(),
                tilstandsrapport.isSlutopgoerelseSendt()
        );

        String getIdSql = """
        SELECT LAST_INSERT_ID()
        """;
        return jdbcTemplate.queryForObject(getIdSql, Integer.class);
    }

    // find lejeaftale ID for en bil (tilstandsrapport)
    public Integer findLejeaftaleIdForBil(String vognnummer) {
        String sql = """
        SELECT lejeaftale_id 
        FROM lejeaftaler 
        WHERE vognnummer = ? 
        AND status = 'afsluttet' 
        ORDER BY lejeaftale_id DESC 
        LIMIT 1
        """;
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, vognnummer);
        } catch (Exception e) {
            return null;
        }
    }
}