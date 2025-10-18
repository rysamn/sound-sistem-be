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
            d.setKodeInventori(rs.getString("kode_inventori"));
            d.setNamaBarang(rs.getString("nama_barang"));
            d.setUkuran(rs.getString("ukuran"));
            d.setMerek(rs.getString("merek"));
            d.setFungsi_equipment(rs.getString("fungsi_equipment"));
            d.setKelengkapan(rs.getString("kelengkapan"));
            d.setStatus(rs.getString("status"));
            d.setFoto(rs.getString("foto"));
            d.setTipe(rs.getString("tipe"));
            d.setDeskripsi(rs.getString("deskripsi"));
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
        String sql = "INSERT INTO inventori (kode_inventori, nama_barang, ukuran, merek, fungsi_equipment, kelengkapan, status, foto, tipe, deskripsi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                d.getKodeInventori(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                d.getStatus(), d.getFoto(), d.getTipe(), d.getDeskripsi());
    }

    public int update(InventoriDto d) {
        String sql = "UPDATE inventori SET kode_inventori=?, nama_barang=?, ukuran=?, merek=?, fungsi_equipment=?, kelengkapan=?, status=?, foto=?, tipe=?, deskripsi=? WHERE id_barang=?";
        return jdbc.update(sql,
                d.getKodeInventori(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                d.getStatus(), d.getFoto(), d.getTipe(), d.getDeskripsi(),
                d.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM inventori WHERE id_barang = ?";
        return jdbc.update(sql, id);
    }
}
