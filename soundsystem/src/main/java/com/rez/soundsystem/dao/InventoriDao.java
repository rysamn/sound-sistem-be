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
            d.setId(rs.getInt("id_barang"));
            d.setNoInventaris(rs.getString("no_inventaris"));
            d.setNamaBarang(rs.getString("nama_barang"));
            d.setUkuran(rs.getString("ukuran"));
            d.setMerek(rs.getString("merek"));
            d.setFungsi_equipment(rs.getString("fungsi_equipment"));
            d.setKelengkapan(rs.getString("kelengkapan"));
            d.setFoto(rs.getString("foto"));
            return d;
        }
    };

    public List<InventoriDto> findAll() {
        String sql = "SELECT * FROM inventori ORDER BY id_barang";
        return jdbc.query(sql, MAPPER);
    }

    public InventoriDto findById(int id) {
        String sql = "SELECT * FROM inventori WHERE id_barang = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public int insert(InventoriDto d) {
        String sql = "INSERT INTO inventori (no_inventaris, nama_barang, ukuran, merek, fungsi_equipment, kelengkapan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                d.getNoInventaris(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                d.getFoto());
    }

    public int update(InventoriDto d) {
        String sql = "UPDATE inventori SET no_inventaris=?, nama_barang=?, ukuran=?, merek=?, fungsi_equipment=?, kelengkapan=?, foto=? WHERE id_barang=?";
        return jdbc.update(sql,
                d.getNoInventaris(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                d.getFoto());
    }

    public int delete(int id) {
        String sql = "DELETE FROM inventori WHERE id_barang = ?";
        return jdbc.update(sql, id);
    }
}
