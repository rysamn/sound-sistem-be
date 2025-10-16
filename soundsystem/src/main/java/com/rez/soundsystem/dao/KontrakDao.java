package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.KontrakDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KontrakDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<KontrakDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public KontrakDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            KontrakDto p = new KontrakDto();
            p.setId(rs.getInt("id_kontrak"));
            p.setIdPelanggan(rs.getInt("id_pelanggan"));
            p.setVenue(rs.getString("venue"));
            p.setAcara(rs.getString("acara"));

            java.sql.Timestamp t1 = rs.getTimestamp("tanggal_mulai");
            if (t1 != null)
                p.setTanggalMulai(t1.toLocalDateTime());

            java.sql.Timestamp t2 = rs.getTimestamp("tanggal_selesai");
            if (t2 != null)
                p.setTanggalSelesai(t2.toLocalDateTime());

            p.setHargaSewa(rs.getDouble("harga_sewa"));
            p.setUangMuka(rs.getDouble("uang_muka"));
            p.setPelunasan(rs.getDouble("pelunasan"));
            p.setMetodeBayar(rs.getString("metode_bayar"));
            p.setNoRekening(rs.getString("no_rekening"));
            p.setStatus(rs.getString("status"));
            p.setKeterangan(rs.getString("keterangan"));
            return p;
        }
    };

    public List<KontrakDto> findAll() {
        // Menggunakan nama tabel 'kontrak'
        return jdbc.query("SELECT * FROM kontrak ORDER BY id_kontrak", ROW_MAPPER);
    }

    public KontrakDto findById(int id) {
        return jdbc.queryForObject("SELECT * FROM kontrak WHERE id_kontrak=?", ROW_MAPPER, id);
    }

    public int insert(KontrakDto p) {
        String sql = "INSERT INTO kontrak (id_pelanggan, venue, acara, tanggal_mulai, tanggal_selesai, harga_sewa, uang_muka, pelunasan, metode_bayar, no_rekening, status, keterangan) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbc.update(
                sql, p.getIdPelanggan(), p.getVenue(), p.getAcara(),
                p.getTanggalMulai() == null ? null : java.sql.Timestamp.valueOf(p.getTanggalMulai()),
                p.getTanggalSelesai() == null ? null : java.sql.Timestamp.valueOf(p.getTanggalSelesai()),
                p.getHargaSewa(), p.getUangMuka(), p.getPelunasan(), p.getMetodeBayar(), p.getNoRekening(),
                p.getStatus(), p.getKeterangan()
        );
    }

    public int update(KontrakDto p) {
        String sql = "UPDATE kontrak SET id_pelanggan=?, venue=?, acara=?, tanggal_mulai=?, tanggal_selesai=?, harga_sewa=?, uang_muka=?, pelunasan=?, metode_bayar=?, no_rekening=?, status=?, keterangan=? "
                + "WHERE id_kontrak=?";
        return jdbc.update(
                sql, p.getIdPelanggan(), p.getVenue(), p.getAcara(),
                p.getTanggalMulai() == null ? null : java.sql.Timestamp.valueOf(p.getTanggalMulai()),
                p.getTanggalSelesai() == null ? null : java.sql.Timestamp.valueOf(p.getTanggalSelesai()),
                p.getHargaSewa(), p.getUangMuka(), p.getPelunasan(), p.getMetodeBayar(), p.getNoRekening(),
                p.getStatus(), p.getKeterangan(), p.getId()
        );
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM kontrak WHERE id_kontrak=?", id);
    }
}
