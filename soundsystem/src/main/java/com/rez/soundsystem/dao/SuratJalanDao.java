package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.SuratJalanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuratJalanDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<SuratJalanDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public SuratJalanDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            SuratJalanDto dto = new SuratJalanDto();
            dto.setId(rs.getInt("id_surat_jalan"));
            dto.setIdKontrak(rs.getInt("id_kontrak"));
            dto.setTanggalKeluar(rs.getDate("tanggal_keluar"));
            dto.setDitandatanganiOleh(rs.getString("ditandatangani_oleh"));
            dto.setSoundEngineer(rs.getString("sound_engineer"));
            return dto;
        }
    };

    public List<SuratJalanDto> findAll() {
        return jdbc.query("SELECT * FROM surat_jalan ORDER BY id_surat_jalan", ROW_MAPPER);
    }

    public SuratJalanDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM surat_jalan WHERE id_surat_jalan = ?", ROW_MAPPER, id);
    }

    public int insert(SuratJalanDto dto) {
        String sql = "INSERT INTO surat_jalan (id_kontrak, tanggal_keluar, ditandatangani_oleh, sound_engineer) VALUES (?, ?, ?, ?)";
        return jdbc.update(
                sql,
                dto.getIdKontrak(),
                dto.getTanggalKeluar(),
                dto.getDitandatanganiOleh(),
                dto.getSoundEngineer());
    }

    public int update(SuratJalanDto dto) {
        String sql = "UPDATE surat_jalan SET id_kontrak = ?, tanggal_keluar = ?, ditandatangani_oleh = ?, sound_engineer = ? WHERE id_surat_jalan = ?";
        return jdbc.update(
                sql,
                dto.getIdKontrak(),
                dto.getTanggalKeluar(),
                dto.getDitandatanganiOleh(),
                dto.getSoundEngineer(),
                dto.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM surat_jalan WHERE id_surat_jalan = ?", id);
    }
}