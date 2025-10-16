package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PenggunaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PenggunaDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<PenggunaDto> ROW_MAPPER = (rs, rowNum) -> {
        PenggunaDto pengguna = new PenggunaDto();
        pengguna.setIdPengguna(rs.getInt("id_pengguna"));
        pengguna.setUsername(rs.getString("username"));
        pengguna.setNamaLengkap(rs.getString("nama_lengkap"));
        pengguna.setRole(rs.getString("role"));
        return pengguna;
    };

    public Optional<PenggunaDto> findByUsernameAndPassword(String username, String password) {
        // PERINGATAN: Query ini tidak aman karena membandingkan password teks biasa.
        String sql = "SELECT id_pengguna, username, nama_lengkap, role FROM pengguna WHERE username = ? AND password = ?";
        try {
            PenggunaDto pengguna = jdbc.queryForObject(sql, ROW_MAPPER, username, password);
            return Optional.ofNullable(pengguna);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}