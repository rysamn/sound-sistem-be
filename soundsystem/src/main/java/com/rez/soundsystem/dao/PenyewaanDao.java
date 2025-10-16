package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.PenyewaanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PenyewaanDao {
    @Autowired private JdbcTemplate jdbc;

    private final RowMapper<PenyewaanDto> M = new RowMapper<>() {
        @Override
        public PenyewaanDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            PenyewaanDto p = new PenyewaanDto();
            p.setId(rs.getInt("id"));
            p.setPelangganId(rs.getInt("pelanggan_id"));
            p.setJobOrderId(rs.getInt("job_order_id"));
            p.setTanggalSewa(rs.getDate("tanggal_sewa"));
            p.setTanggalKembali(rs.getDate("tanggal_kembali"));
            p.setTotalBiaya(rs.getDouble("total_biaya"));
            p.setStatus(rs.getString("status"));
            p.setKeterangan(rs.getString("keterangan"));
            return p;
        }
    };

    public List<PenyewaanDto> findAll(){ return jdbc.query("SELECT * FROM penyewaan ORDER BY id", M); }
    public PenyewaanDto findById(int id){ return jdbc.queryForObject("SELECT * FROM penyewaan WHERE id=?", M, id); }
    public int insert(PenyewaanDto p){ return jdbc.update("INSERT INTO penyewaan (pelanggan_id, job_order_id, tanggal_sewa, tanggal_kembali, total_biaya, status, keterangan) VALUES (?,?,?,?,?,?,?)",
                p.getPelangganId(), p.getJobOrderId(), p.getTanggalSewa(), p.getTanggalKembali(), p.getTotalBiaya(), p.getStatus(), p.getKeterangan()); }
    public int update(PenyewaanDto p){ return jdbc.update("UPDATE penyewaan SET pelanggan_id=?, job_order_id=?, tanggal_sewa=?, tanggal_kembali=?, total_biaya=?, status=?, keterangan=? WHERE id=?",
                p.getPelangganId(), p.getJobOrderId(), p.getTanggalSewa(), p.getTanggalKembali(), p.getTotalBiaya(), p.getStatus(), p.getKeterangan(), p.getId()); }
    public int delete(int id){ return jdbc.update("DELETE FROM penyewaan WHERE id=?", id); }
}
