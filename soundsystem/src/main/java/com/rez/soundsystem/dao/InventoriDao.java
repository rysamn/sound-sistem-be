package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.InventoriDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoriDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<InventoriDto> MAPPER = new RowMapper<InventoriDto>() {
        @Override
        public InventoriDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            InventoriDto d = new InventoriDto();
            d.setId(rs.getInt("id"));
            d.setKodeInventori(rs.getString("kode_inventori"));
            d.setNamaEquipment(rs.getString("nama_equipment"));
            d.setUkuran(rs.getString("ukuran"));
            d.setMerek(rs.getString("merek"));
            d.setFungsiEquipment(rs.getString("fungsi_equipment"));
            d.setKelengkapan(rs.getString("kelengkapan"));
            d.setStatus(rs.getString("status"));
            d.setFoto(rs.getString("foto"));
            return d;
        }
    };

    public List<InventoriDto> findAll() {
        String sql = "SELECT * FROM inventori ORDER BY id";
        return jdbc.query(sql, MAPPER);
    }

    public InventoriDto findById(int id) {
        String sql = "SELECT * FROM inventori WHERE id = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public int insert(InventoriDto d) {
        String sql = "INSERT INTO inventori (kode_inventori, nama_equipment, ukuran, merek, fungsi_equipment, kelengkapan, status, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                d.getKodeInventori(), d.getNamaEquipment(), d.getUkuran(),
                d.getMerek(), d.getFungsiEquipment(), d.getKelengkapan(),
                d.getStatus(), d.getFoto());
    }

    public int update(InventoriDto d) {
        String sql = "UPDATE inventori SET kode_inventori=?, nama_equipment=?, ukuran=?, merek=?, fungsi_equipment=?, kelengkapan=?, status=?, foto=? WHERE id=?";
        return jdbc.update(sql,
                d.getKodeInventori(), d.getNamaEquipment(), d.getUkuran(),
                d.getMerek(), d.getFungsiEquipment(), d.getKelengkapan(),
                d.getStatus(), d.getFoto(), d.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM inventori WHERE id = ?";
        return jdbc.update(sql, id);
    }
}
