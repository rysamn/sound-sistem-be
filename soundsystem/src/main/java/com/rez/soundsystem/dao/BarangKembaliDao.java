package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.BarangKembaliDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BarangKembaliDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<BarangKembaliDto> MAPPER = new RowMapper<>() {
        @Override
        public BarangKembaliDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            BarangKembaliDto b = new BarangKembaliDto();
            b.setId(rs.getInt("id_pengembalian"));
            b.setIdSuratJalan(rs.getInt("surat_jalan_id"));
            b.setTanggalKembali(rs.getDate("tanggal_kembali"));
            b.setKondisiBarang(rs.getString("kondisi_barang"));
            b.setPenanggungJawaban(rs.getString("penanggung_jawaban"));
            b.setSoundEngineer(rs.getString("sound_engineer"));
            b.setKeterangan(rs.getString("keterangan"));
            return b;
        }
    };

    public List<BarangKembaliDto> findAll() {
        // Menggunakan kolom id_pengembalian yang konsisten dengan RowMapper
        String sql = "SELECT * FROM barang_kembali ORDER BY id_pengembalian";
        return jdbcTemplate.query(sql, MAPPER);
    }

    public BarangKembaliDto findById(int id) {
        String sql = "SELECT * FROM barang_kembali WHERE id_pengembalian = ?";
        return jdbcTemplate.queryForObject(sql, MAPPER, id);
    }

    public int insert(BarangKembaliDto b) {
        // Menyesuaikan kolom dan getter dengan RowMapper dan DTO
        String sql = "INSERT INTO barang_kembali (surat_jalan_id, tanggal_kembali, kondisi_barang, penanggung_jawaban, sound_engineer, keterangan) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, b.getIdSuratJalan(), b.getTanggalKembali(), b.getKondisiBarang(),
                b.getPenanggungJawaban(), b.getSoundEngineer(), b.getKeterangan());
    }

    public int update(BarangKembaliDto b) {
        // Menyesuaikan kolom, getter, dan WHERE clause dengan RowMapper dan DTO
        String sql = "UPDATE barang_kembali SET surat_jalan_id = ?, tanggal_kembali = ?, kondisi_barang = ?, penanggung_jawaban = ?, sound_engineer = ?, keterangan = ? WHERE id_pengembalian = ?";
        return jdbcTemplate.update(sql, b.getIdSuratJalan(), b.getTanggalKembali(), b.getKondisiBarang(),
                b.getPenanggungJawaban(), b.getSoundEngineer(), b.getKeterangan(), b.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM barang_kembali WHERE id_pengembalian = ?";
        return jdbcTemplate.update(sql, id);
    }
}
