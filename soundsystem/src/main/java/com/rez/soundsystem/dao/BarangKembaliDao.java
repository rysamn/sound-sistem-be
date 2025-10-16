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
            b.setId(rs.getInt("id"));
            b.setBarangKeluarId(rs.getInt("barang_keluar_id"));
            b.setTanggalKembali(rs.getDate("tanggal_kembali"));
            b.setKondisi(rs.getString("kondisi"));
            b.setKeterangan(rs.getString("keterangan"));
            return b;
        }
    };

    public List<BarangKembaliDto> findAll() {
        String sql = "SELECT * FROM barang_kembali ORDER BY id";
        return jdbcTemplate.query(sql, MAPPER);
    }

    public BarangKembaliDto findById(int id) {
        String sql = "SELECT * FROM barang_kembali WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, MAPPER, id);
    }

    public int insert(BarangKembaliDto b) {
        String sql = "INSERT INTO barang_kembali (barang_keluar_id, tanggal_kembali, kondisi, keterangan) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, b.getBarangKeluarId(), b.getTanggalKembali(), b.getKondisi(),
                b.getKeterangan());
    }

    public int update(BarangKembaliDto b) {
        String sql = "UPDATE barang_kembali SET barang_keluar_id = ?, tanggal_kembali = ?, kondisi = ?, keterangan = ? WHERE id = ?";
        return jdbcTemplate.update(sql, b.getBarangKeluarId(), b.getTanggalKembali(), b.getKondisi(), b.getKeterangan(),
                b.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM barang_kembali WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
