package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PelangganDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PelangganDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<PelangganDto> M = new RowMapper<>() {
        @Override
        public PelangganDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            PelangganDto p = new PelangganDto();
            p.setIdPelanggan(rs.getInt("id_pelanggan"));
            p.setNama(rs.getString("nama"));
            p.setNoTelp(rs.getString("no_telp"));
            p.setEmail(rs.getString("email"));
            p.setAlamat(rs.getString("alamat"));
            return p;
        }
    };

    public List<PelangganDto> findAll() {
        return jdbc.query("SELECT * FROM pelanggan ORDER BY id_pelanggan", M);
    }

    public PelangganDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM pelanggan WHERE id_pelanggan = ?", M, id);
    }

    public int insert(PelangganDto p) {
        return jdbc.update("INSERT INTO pelanggan (nama, no_telp, email, alamat) VALUES (?,?,?,?)",
                p.getNama(), p.getNoTelp(), p.getEmail(), p.getAlamat());
    }

    public int update(PelangganDto p) {
        return jdbc.update("UPDATE pelanggan SET nama=?, no_telp=?, email=?, alamat=? WHERE id_pelanggan=?",
                p.getNama(), p.getNoTelp(), p.getEmail(), p.getAlamat(), p.getIdPelanggan());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM pelanggan WHERE id_pelanggan=?", id);
    }
}
