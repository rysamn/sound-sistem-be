package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.TeamHandlingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamHandlingDao {
    @Autowired private JdbcTemplate jdbc;

    private final RowMapper<TeamHandlingDto> M = new RowMapper<>() {
        @Override
        public TeamHandlingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TeamHandlingDto t = new TeamHandlingDto();
            t.setId(rs.getInt("id"));
            t.setNama(rs.getString("nama"));
            t.setJabatan(rs.getString("jabatan"));
            t.setFee(rs.getDouble("fee"));
            t.setStatus(rs.getString("status"));
            return t;
        }
    };

    public List<TeamHandlingDto> findAll(){ return jdbc.query("SELECT * FROM team_handling ORDER BY id", M); }
    public TeamHandlingDto findById(int id){ return jdbc.queryForObject("SELECT * FROM team_handling WHERE id=?", M, id); }
    public int insert(TeamHandlingDto t){ return jdbc.update("INSERT INTO team_handling (nama,jabatan,fee,status) VALUES (?,?,?,?)", t.getNama(), t.getJabatan(), t.getFee(), t.getStatus()); }
    public int update(TeamHandlingDto t){ return jdbc.update("UPDATE team_handling SET nama=?, jabatan=?, fee=?, status=? WHERE id=?", t.getNama(), t.getJabatan(), t.getFee(), t.getStatus(), t.getId()); }
    public int delete(int id){ return jdbc.update("DELETE FROM team_handling WHERE id=?", id); }
}
