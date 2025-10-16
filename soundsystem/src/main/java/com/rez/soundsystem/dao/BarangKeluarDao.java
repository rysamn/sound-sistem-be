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
    private final RowMapper<BarangKeluarDto> M = new RowMapper<>() {
        @Override
        public BarangKeluarDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            BarangKeluarDto b = new BarangKeluarDto();
            b.setId(rs.getInt("id"));
            b.setJobOrderId(rs.getInt("job_order_id"));
            b.setInventoriId(rs.getInt("inventori_id"));
            b.setJumlah(rs.getInt("jumlah"));
            b.setTanggalKeluar(rs.getDate("tanggal_keluar"));
            b.setPenanggungJawabGudang(rs.getString("penanggung_jawab_gudang"));
            b.setSoundEngineer(rs.getString("sound_engineer"));
            b.setStatus(rs.getString("status"));
            return b;
        }
    };

    public List<BarangKeluarDto> findAll() {
        return jdbc.query("SELECT * FROM barang_keluar ORDER BY id", M);
    }

    public BarangKeluarDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM barang_keluar WHERE id=?", M, id);
    }

    public int insert(BarangKeluarDto b) {
        return jdbc.update(
                "INSERT INTO barang_keluar (job_order_id,inventori_id,jumlah,tanggal_keluar,penanggung_jawab_gudang,sound_engineer,status) VALUES (?,?,?,?,?,?,?)",
                b.getJobOrderId(), b.getInventoriId(), b.getJumlah(), b.getTanggalKeluar(),
                b.getPenanggungJawabGudang(), b.getSoundEngineer(), b.getStatus());
    }

    public int update(BarangKeluarDto b) {
        return jdbc.update(
                "UPDATE barang_keluar SET job_order_id=?, inventori_id=?, jumlah=?, tanggal_keluar=?, penanggung_jawab_gudang=?, sound_engineer=?, status=? WHERE id=?",
                b.getJobOrderId(), b.getInventoriId(), b.getJumlah(), b.getTanggalKeluar(),
                b.getPenanggungJawabGudang(), b.getSoundEngineer(), b.getStatus(), b.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM barang_keluar WHERE id=?", id);
    }
}
