package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.JurnalDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JurnalDetailDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<JurnalDetailDto> ROW_MAPPER = (rs, rowNum) -> {
        JurnalDetailDto dto = new JurnalDetailDto();
        dto.setId(rs.getInt("id_detail"));
        dto.setIdJurnal(rs.getInt("id_jurnal"));
        dto.setIdAkun(rs.getInt("id_akun"));
        dto.setDebit(rs.getDouble("debit"));
        dto.setKredit(rs.getDouble("kredit"));
        return dto;
    };

    public List<JurnalDetailDto> findAll() {
        return jdbc.query("SELECT * FROM jurnal_detail ORDER BY id_detail", ROW_MAPPER);
    }

    public JurnalDetailDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM jurnal_detail WHERE id_detail = ?", ROW_MAPPER, id);
    }

    public List<JurnalDetailDto> findByJurnalId(int idJurnal) {
        return jdbc.query("SELECT * FROM jurnal_detail WHERE id_jurnal = ? ORDER BY id_detail", ROW_MAPPER, idJurnal);
    }

    public int insert(JurnalDetailDto dto) {
        String sql = "INSERT INTO jurnal_detail (id_jurnal, id_akun, debit, kredit) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, dto.getIdJurnal(), dto.getIdAkun(), dto.getDebit(), dto.getKredit());
    }

    public int update(JurnalDetailDto dto) {
        String sql = "UPDATE jurnal_detail SET id_jurnal = ?, id_akun = ?, debit = ?, kredit = ? WHERE id_detail = ?";
        return jdbc.update(
                sql, dto.getIdJurnal(), dto.getIdAkun(),
                dto.getDebit(), dto.getKredit(), dto.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM jurnal_detail WHERE id_detail = ?", id);
    }
}