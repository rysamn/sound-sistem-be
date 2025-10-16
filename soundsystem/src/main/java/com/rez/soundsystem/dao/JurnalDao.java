package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.JurnalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JurnalDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<JurnalDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public JurnalDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            JurnalDto dto = new JurnalDto();
            dto.setIdJurnal(rs.getInt("id_jurnal"));
            dto.setTanggal(rs.getDate("tanggal"));
            dto.setJenis(rs.getString("jenis"));
            dto.setKeterangan(rs.getString("keterangan"));
            dto.setTotal(rs.getDouble("total"));
            return dto;
        }
    };

    public List<JurnalDto> findAll() {
        return jdbc.query("SELECT * FROM jurnal ORDER BY id_jurnal", ROW_MAPPER);
    }

    public JurnalDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM jurnal WHERE id_jurnal = ?", ROW_MAPPER, id);
    }

    public int insert(JurnalDto dto) {
        String sql = "INSERT INTO jurnal (tanggal, jenis, keterangan, total) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, dto.getTanggal(), dto.getJenis(), dto.getKeterangan(), dto.getTotal());
    }

    public int update(JurnalDto dto) {
        String sql = "UPDATE jurnal SET tanggal = ?, jenis = ?, keterangan = ?, total = ? WHERE id_jurnal = ?";
        return jdbc.update(
                sql,
                dto.getTanggal(),
                dto.getJenis(),
                dto.getKeterangan(),
                dto.getTotal(),
                dto.getIdJurnal());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM jurnal WHERE id_jurnal = ?", id);
    }
}