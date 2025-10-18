package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PenggunaDto;
import com.rez.soundsystem.dto.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PenggunaDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<PenggunaDto> ROW_MAPPER = (rs, rowNum) -> {
        PenggunaDto pengguna = new PenggunaDto();
        pengguna.setIdPengguna(rs.getInt("id_pengguna"));
        pengguna.setUsername(rs.getString("username"));
        // Password tidak diambil untuk alasan keamanan
        pengguna.setNamaLengkap(rs.getString("nama_lengkap"));
        pengguna.setEmail(rs.getString("email"));
        pengguna.setRole(rs.getString("role"));
        pengguna.setTanggalDibuat(rs.getTimestamp("tanggal_dibuat") != null ? rs.getTimestamp("tanggal_dibuat").toLocalDateTime() : null);
        return pengguna;
    };

    public Optional<PenggunaDto> findByUsernameAndPassword(String username, String password) {
        // PERINGATAN: Query ini tidak aman karena membandingkan password teks biasa.        
        String sql = "SELECT * FROM pengguna WHERE username = ? AND password = ?"; // Password masih plain text
        try {
            PenggunaDto pengguna = jdbc.queryForObject(sql, ROW_MAPPER, username, password);
            return Optional.ofNullable(pengguna);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<PenggunaDto> findByUsername(String username) {
        String sql = "SELECT * FROM pengguna WHERE username = ?";
        try {
            PenggunaDto pengguna = jdbc.queryForObject(sql, ROW_MAPPER, username);
            return Optional.ofNullable(pengguna);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<PenggunaDto> findByEmail(String email) {
        String sql = "SELECT * FROM pengguna WHERE email = ?";
        try {
            PenggunaDto pengguna = jdbc.queryForObject(sql, ROW_MAPPER, email);
            return Optional.ofNullable(pengguna);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int insert(SignUpRequestDto user) {
        // PERINGATAN: Menyimpan password sebagai plain text sangat tidak aman.
        // Untuk produksi, password harus di-hash menggunakan BCrypt.
        // PERINGATAN KEAMANAN: Mengizinkan role dari input pengguna sangat berisiko.
        String sql = "INSERT INTO pengguna (username, password, nama_lengkap, email, role, tanggal_dibuat) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql, user.getUsername(), user.getPassword(), user.getNamaLengkap(), user.getEmail(), user.getRole(), LocalDateTime.now());
    }
}