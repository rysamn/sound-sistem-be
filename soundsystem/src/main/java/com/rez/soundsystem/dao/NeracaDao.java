package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.NeracaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NeracaDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<NeracaDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public NeracaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            NeracaDto dto = new NeracaDto();
            dto.setId(rs.getInt("id_neraca"));
            dto.setPeriode(rs.getString("periode"));
            dto.setTotalAktiva(rs.getDouble("total_aktiva"));
            dto.setTotalKewajiban(rs.getDouble("total_kewajiban"));
            dto.setTotalModal(rs.getDouble("total_modal"));
            return dto;
        }
    };

    public List<NeracaDto> findAll() {
        return jdbc.query("SELECT * FROM neraca ORDER BY id_neraca", ROW_MAPPER);
    }

    public NeracaDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM neraca WHERE id_neraca = ?", ROW_MAPPER, id);
    }

    public int insert(NeracaDto dto) {
        String sql = "INSERT INTO neraca (periode, total_aktiva, total_kewajiban, total_modal) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, dto.getPeriode(), dto.getTotalAktiva(), dto.getTotalKewajiban(), dto.getTotalModal());
    }

    public int update(NeracaDto dto) {
        String sql = "UPDATE neraca SET periode = ?, total_aktiva = ?, total_kewajiban = ?, total_modal = ? WHERE id_neraca = ?";
        return jdbc.update(
                sql,
                dto.getPeriode(),
                dto.getTotalAktiva(),
                dto.getTotalKewajiban(),
                dto.getTotalModal(),
                dto.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM neraca WHERE id_neraca = ?", id);
    }
}