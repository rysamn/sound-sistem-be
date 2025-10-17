package com.rez.soundsystem.dao;

import com.rez.soundsystem.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDao {
    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<InvoiceDto> ROW_MAPPER = new RowMapper<>() {
        @Override
        public InvoiceDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceDto dto = new InvoiceDto();
            dto.setId(rs.getInt("id_invoice"));
            dto.setIdKontrak(rs.getInt("id_kontrak"));
            dto.setTanggalInvoice(rs.getDate("tanggal_invoice"));
            dto.setTotalTagihan(rs.getDouble("total_tagihan"));
            dto.setStatus(rs.getString("status_pembayaran"));
            dto.setTanggalPembayaran(rs.getDate("tanggal_pembayaran"));
            return dto;
        }
    };

    public List<InvoiceDto> findAll() {
        return jdbc.query("SELECT * FROM invoice ORDER BY id_invoice", ROW_MAPPER);
    }

    public InvoiceDto findById(int id) {
        // Menggunakan queryForObject yang akan melempar exception jika tidak ditemukan
        return jdbc.queryForObject("SELECT * FROM invoice WHERE id_invoice = ?", ROW_MAPPER, id);
    }

    public int insert(InvoiceDto dto) {
        String sql = "INSERT INTO invoice (id_kontrak, tanggal_invoice, total_tagihan, status_pembayaran, tanggal_pembayaran) "
                + "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(
                sql,
                dto.getIdKontrak(),
                dto.getTanggalInvoice(),
                dto.getTotalTagihan(),
                dto.getStatus(),
                dto.getTanggalPembayaran());
    }

    public int update(InvoiceDto dto) {
        String sql = "UPDATE invoice SET id_kontrak = ?, tanggal_invoice = ?, total_tagihan = ?, status_pembayaran = ?, tanggal_pembayaran = ? "
                + "WHERE id_invoice = ?";
        return jdbc.update(
                sql,
                dto.getIdKontrak(),
                dto.getTanggalInvoice(),
                dto.getTotalTagihan(),
                dto.getStatus(),
                dto.getTanggalPembayaran(),
                dto.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM invoice WHERE id_invoice = ?", id);
    }
}