package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PembayaranDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PembayaranDao {
    @Autowired
    private JdbcTemplate jdbc;
    private final RowMapper<PembayaranDto> M = new RowMapper<>() {
        @Override
        public PembayaranDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            PembayaranDto p = new PembayaranDto();
            p.setId(rs.getInt("id"));
            p.setJobOrderId(rs.getInt("job_order_id"));
            p.setTanggalBayar(rs.getDate("tanggal_bayar"));
            p.setJumlah(rs.getDouble("jumlah"));
            p.setMetode(rs.getString("metode"));
            p.setKeterangan(rs.getString("keterangan"));
            return p;
        }
    };

    public List<PembayaranDto> findAll() {
        return jdbc.query("SELECT * FROM pembayaran ORDER BY id", M);
    }

    public PembayaranDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM pembayaran WHERE id=?", M, id);
    }

    public int insert(PembayaranDto p) {
        return jdbc.update(
                "INSERT INTO pembayaran (job_order_id,tanggal_bayar,jumlah,metode,keterangan) VALUES (?,?,?,?,?)",
                p.getJobOrderId(), p.getTanggalBayar(), p.getJumlah(), p.getMetode(), p.getKeterangan());
    }

    public int update(PembayaranDto p) {
        return jdbc.update(
                "UPDATE pembayaran SET job_order_id=?, tanggal_bayar=?, jumlah=?, metode=?, keterangan=? WHERE id=?",
                p.getJobOrderId(), p.getTanggalBayar(), p.getJumlah(), p.getMetode(), p.getKeterangan(), p.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM pembayaran WHERE id=?", id);
    }
}
