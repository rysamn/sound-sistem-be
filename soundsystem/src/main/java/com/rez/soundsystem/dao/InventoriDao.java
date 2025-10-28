package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.dto.InventoriResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Repository
public class InventoriDao {

    @Autowired
    private JdbcTemplate jdbc;

    // RowMapper ini sekarang akan menghasilkan InventoriResponseDto secara langsung
    private final RowMapper<InventoriResponseDto> RESPONSE_MAPPER = new RowMapper<InventoriResponseDto>() {
        @Override
        public InventoriResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            InventoriResponseDto d = new InventoriResponseDto();
            d.setId(rs.getInt("id_barang")); // Pastikan nama kolom di DB adalah id_barang
            d.setNoInventaris(rs.getString("no_inventaris"));
            d.setNamaBarang(rs.getString("nama_barang"));
            d.setUkuran(rs.getString("ukuran"));
            d.setMerek(rs.getString("merek"));
            d.setFungsi_equipment(rs.getString("fungsi_equipment"));
            d.setKelengkapan(rs.getString("kelengkapan"));
            byte[] fotoBytes = rs.getBytes("foto");
            if (fotoBytes != null && fotoBytes.length > 0) {
                d.setFoto(Base64.getEncoder().encodeToString(fotoBytes));
            }
            return d;
        }
    };

    public List<InventoriResponseDto> findAll(Pageable pageable) {
        String sql = "SELECT * FROM inventori ORDER BY id_barang LIMIT ? OFFSET ?";
        return jdbc.query(sql, RESPONSE_MAPPER, pageable.getPageSize(), pageable.getOffset());
    }

    public long count() {
        String sql = "SELECT count(*) FROM inventori";
        Long total = jdbc.queryForObject(sql, Long.class);
        return total != null ? total : 0;
    }

    public InventoriResponseDto findById(int id) {
        String sql = "SELECT * FROM inventori WHERE id_barang = ?";
        try {
            return jdbc.queryForObject(sql, RESPONSE_MAPPER, id);
        } catch (EmptyResultDataAccessException e) {
            // Mengembalikan null jika tidak ada data yang ditemukan,
            // ini akan ditangani di service layer.
            return null;
        }
    }

    public int insert(InventoriDto d, byte[] fotoBytes) {
        String sql = "INSERT INTO inventori (no_inventaris, nama_barang, ukuran, merek, fungsi_equipment, kelengkapan, foto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                d.getNoInventaris(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                fotoBytes);
    }

    public int update(InventoriDto d, byte[] fotoBytes) {
        String sql = "UPDATE inventori SET no_inventaris=?, nama_barang=?, ukuran=?, merek=?, fungsi_equipment=?, kelengkapan=?, foto=? WHERE id_barang=?";
        return jdbc.update(sql,
                d.getNoInventaris(), d.getNamaBarang(), d.getUkuran(),
                d.getMerek(), d.getFungsi_equipment(), d.getKelengkapan(),
                fotoBytes, d.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM inventori WHERE id_barang = ?";
        return jdbc.update(sql, id);
    }
}
