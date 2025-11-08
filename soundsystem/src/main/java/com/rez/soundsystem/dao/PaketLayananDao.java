package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PaketLayananDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaketLayananDao {
    
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<PaketLayananDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public PaketLayananDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaketLayananDto p = new PaketLayananDto();
            p.setId(rs.getInt("id_paket"));
            p.setNamaPaket(rs.getString("nama_paket"));
            p.setDeskripsi(rs.getString("deskripsi"));
            p.setHargaPerJam(rs.getDouble("harga_per_jam"));
            p.setMinimumJam(rs.getInt("minimum_jam"));
            p.setIncludesRecording(rs.getBoolean("includes_recording"));
            p.setIncludesLiveAudio(rs.getBoolean("includes_live_audio"));
            p.setIncludesVideoRecording(rs.getBoolean("includes_video_recording"));
            p.setJenisStudio(rs.getString("jenis_studio"));
            return p;
        }
    };

    public List<PaketLayananDto> findAll() {
        return jdbc.query("SELECT * FROM paket_layanan ORDER BY id_paket", ROW_MAPPER);
    }

    public PaketLayananDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM paket_layanan WHERE id_paket=?", ROW_MAPPER, id);
    }

    public PaketLayananDto findByNama(String nama) {
        return jdbc.queryForObject("SELECT * FROM paket_layanan WHERE nama_paket=?", ROW_MAPPER, nama);
    }
}