package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.JobOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JobOrderDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<JobOrderDto> M = new RowMapper<>() {
        @Override
        public JobOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobOrderDto j = new JobOrderDto();
            j.setId(rs.getInt("id"));
            j.setNamaPenyewa(rs.getString("nama_penyewa"));
            j.setVenue(rs.getString("venue"));
            j.setAcara(rs.getString("acara"));
            java.sql.Timestamp t1 = rs.getTimestamp("tanggal_mulai");
            if (t1 != null)
                j.setTanggalMulai(t1.toLocalDateTime());
            java.sql.Timestamp t2 = rs.getTimestamp("tanggal_selesai");
            if (t2 != null)
                j.setTanggalSelesai(t2.toLocalDateTime());
            j.setHargaSewa(rs.getDouble("harga_sewa"));
            j.setStatusPembayaran(rs.getString("status_pembayaran"));
            j.setMetodeBayar(rs.getString("metode_bayar"));
            j.setNoRekening(rs.getString("no_rekening"));
            j.setTanggalOrder(rs.getDate("tanggal_order"));
            return j;
        }
    };

    public List<JobOrderDto> findAll() {
        return jdbc.query("SELECT * FROM job_order ORDER BY id", M);
    }

    public JobOrderDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM job_order WHERE id=?", M, id);
    }

    public int insert(JobOrderDto j) {
        return jdbc.update(
                "INSERT INTO job_order (nama_penyewa,venue,acara,tanggal_mulai,tanggal_selesai,harga_sewa,status_pembayaran,metode_bayar,no_rekening,tanggal_order) VALUES (?,?,?,?,?,?,?,?,?,?)",
                j.getNamaPenyewa(), j.getVenue(), j.getAcara(),
                j.getTanggalMulai() == null ? null : java.sql.Timestamp.valueOf(j.getTanggalMulai()),
                j.getTanggalSelesai() == null ? null : java.sql.Timestamp.valueOf(j.getTanggalSelesai()),
                j.getHargaSewa(), j.getStatusPembayaran(), j.getMetodeBayar(), j.getNoRekening(), j.getTanggalOrder());
    }

    public int update(JobOrderDto j) {
        return jdbc.update(
                "UPDATE job_order SET nama_penyewa=?, venue=?, acara=?, tanggal_mulai=?, tanggal_selesai=?, harga_sewa=?, status_pembayaran=?, metode_bayar=?, no_rekening=?, tanggal_order=? WHERE id=?",
                j.getNamaPenyewa(), j.getVenue(), j.getAcara(),
                j.getTanggalMulai() == null ? null : java.sql.Timestamp.valueOf(j.getTanggalMulai()),
                j.getTanggalSelesai() == null ? null : java.sql.Timestamp.valueOf(j.getTanggalSelesai()),
                j.getHargaSewa(), j.getStatusPembayaran(), j.getMetodeBayar(), j.getNoRekening(), j.getTanggalOrder(),
                j.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM job_order WHERE id=?", id);
    }
}
