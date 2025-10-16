package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.BarangKeluarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BarangKeluarDao {
    @Autowired
    private JdbcTemplate jdbc;
    private final RowMapper<BarangKeluarDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public BarangKeluarDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            BarangKeluarDto b = new BarangKeluarDto();
            b.setId(rs.getInt("id_barang_keluar"));
            b.setIdSuratJalan(rs.getInt("id_surat_jalan"));
            b.setKodeInventori(rs.getInt("kode_inventori"));
            b.setJumlah(rs.getInt("jumlah"));
            b.setPenanggungJawabGudang(rs.getString("penanggung_jawab_gudang"));
            b.setSoundEngineer(rs.getString("sound_engineer"));
            b.setStatus(rs.getString("status"));
            return b;
        }
    };

    public List<BarangKeluarDto> findAll() {
        return jdbc.query("SELECT * FROM barang_keluar ORDER BY id_barang_keluar", ROW_MAPPER);
    }

    public BarangKeluarDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM barang_keluar WHERE id_barang_keluar=?", ROW_MAPPER, id);
    }

    public int insert(BarangKeluarDto b) {
        String sql = "INSERT INTO barang_keluar (id_surat_jalan, kode_inventori, jumlah, penanggung_jawab_gudang, sound_engineer, status) VALUES (?,?,?,?,?,?)";
        return jdbc.update(
                sql,
                b.getIdSuratJalan(), b.getKodeInventori(), b.getJumlah(),
                b.getPenanggungJawabGudang(), b.getSoundEngineer(), b.getStatus());
    }

    public int update(BarangKeluarDto b) {
        String sql = "UPDATE barang_keluar SET id_surat_jalan=?, kode_inventori=?, jumlah=?, penanggung_jawab_gudang=?, sound_engineer=?, status=? WHERE id_barang_keluar=?";
        return jdbc.update(
                sql,
                b.getIdSuratJalan(), b.getKodeInventori(), b.getJumlah(),
                b.getPenanggungJawabGudang(), b.getSoundEngineer(), b.getStatus(),
                b.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM barang_keluar WHERE id_barang_keluar=?", id);
    }
}
