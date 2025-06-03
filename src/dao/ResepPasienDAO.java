package dao;

import model.ResepPasien;
import klinik_pbo.koneksi;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.sql.Date;


public class ResepPasienDAO {
    private Connection conn;

    public ResepPasienDAO() {
        this.conn = koneksi.getKoneksi();
    }

    // Ambil semua data resep lengkap
    public static List<ResepPasien> getAllResepLengkap() {
        List<ResepPasien> list = new ArrayList<>();
        Connection conn = koneksi.getKoneksi();

        try {
            String sql = """
                SELECT r.idResep, r.penyakit, r.tanggal, r.status,
                       p.nama AS namaPasien,
                       d.nama AS namaDokter,
                       o.namaObat
                FROM resep r
                JOIN pasien p ON r.idPasien = p.idPasien
                JOIN dokter d ON r.idDokter = d.idDokter
                LEFT JOIN obat o ON r.idObat = o.idObat
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ResepPasien resep = new ResepPasien(
                    rs.getString("idResep"),
                    rs.getString("penyakit"),
                    rs.getDate("tanggal"),
                    rs.getString("namaPasien"),
                    rs.getString("namaDokter"),
                    rs.getString("namaObat"),
                    rs.getString("status")
                );
                list.add(resep);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Insert resep
    public void insertResep(String idResep, String penyakit, Date tanggal, String idPasien, String idDokter, String idObat, String status) throws SQLException {
    String query = "INSERT INTO resep (idResep, penyakit, tanggal, idPasien, idDokter, idObat, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, idResep);
        ps.setString(2, penyakit);
        ps.setDate(3, tanggal);
        ps.setString(4, idPasien);
        ps.setString(5, idDokter);
        ps.setString(6, idObat);
        ps.setString(7, status);
        ps.executeUpdate();
    }
}


    // Update resep
    public void updateResep(String idResep, String penyakit, Date tanggal, String idPasien, String idDokter, String idObat, String status) throws SQLException {
        String query = "UPDATE resep SET penyakit=?, tanggal=?, idPasien=?, idDokter=?, idObat=?, status=? WHERE idResep=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, penyakit);
            ps.setDate(2, tanggal);
            ps.setString(3, idPasien);
            ps.setString(4, idDokter);
            ps.setString(5, idObat);
            ps.setString(6, status);
            ps.setString(7, idResep);
            ps.executeUpdate();
        }
    }
    
    public void updateResepTanpaStatus(String idResep, String penyakit, Date tanggal, String idPasien, String idDokter, String idObat) throws SQLException {
    String query = "UPDATE resep SET penyakit=?, tanggal=?, idPasien=?, idDokter=?, idObat=? WHERE idResep=?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, penyakit);
        ps.setDate(2, tanggal);
        ps.setString(3, idPasien);
        ps.setString(4, idDokter);
        ps.setString(5, idObat);
        ps.setString(6, idResep);
        ps.executeUpdate();
        }
    }
    
    public void updateStatusResep(String idResep, String status) throws SQLException {
    String query = "UPDATE resep SET status=? WHERE idResep=?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, status);
        ps.setString(2, idResep);
        ps.executeUpdate();
    }
}



    // Delete resep
    public void deleteResep(String idResep) throws SQLException {
        String query = "DELETE FROM resep WHERE idResep = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, idResep);
            ps.executeUpdate();
        }
    }

    // Ambil semua data resep (untuk refresh tabel)
    public List<ResepPasien> getAllResep() throws SQLException {
        List<ResepPasien> list = new ArrayList<>();
        String query = """
            SELECT r.idResep, r.penyakit, r.tanggal, r.status,
                   p.nama AS namaPasien,
                   d.nama AS namaDokter,
                   o.namaObat
            FROM resep r
            JOIN pasien p ON r.idPasien = p.idPasien
            JOIN dokter d ON r.idDokter = d.idDokter
            LEFT JOIN obat o ON r.idObat = o.idObat
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new ResepPasien(
                    rs.getString("idResep"),
                    rs.getString("penyakit"),
                    rs.getDate("tanggal"),
                    rs.getString("namaPasien"),
                    rs.getString("namaDokter"),
                    rs.getString("namaObat"),
                    rs.getString("status")
                ));
            }
        }

        return list;
    }

    // Get data pasien untuk combobox
    public Map<String, String> getAllPasienForCombo() throws SQLException {
        Map<String, String> pasienMap = new HashMap<>();
        String query = "SELECT idPasien, nama FROM pasien";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pasienMap.put(rs.getString("nama"), rs.getString("idPasien"));
            }
        }
        return pasienMap;
    }

    // Get data dokter untuk combobox
    public Map<String, String> getAllDokterForCombo() throws SQLException {
        Map<String, String> dokterMap = new HashMap<>();
        String query = "SELECT idDokter, nama FROM dokter";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                dokterMap.put(rs.getString("nama"), rs.getString("idDokter"));
            }
        }
        return dokterMap;
    }

    // Get data obat untuk combobox
    public Map<String, String> getAllObatForCombo() throws SQLException {
        Map<String, String> obatMap = new HashMap<>();
        String query = "SELECT idObat, namaObat FROM obat";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                obatMap.put(rs.getString("namaObat"), rs.getString("idObat"));
            }
        }
        return obatMap;
    }

    // Get data resep berdasarkan ID
    public Map<String, String> getResepById(String idResep) throws SQLException {
        Map<String, String> data = new HashMap<>();
        String query = """
            SELECT r.*, p.nama AS namaPasien, d.nama AS namaDokter, o.namaObat
            FROM resep r
            JOIN pasien p ON r.idPasien = p.idPasien
            JOIN dokter d ON r.idDokter = d.idDokter
            LEFT JOIN obat o ON r.idObat = o.idObat
            WHERE r.idResep = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, idResep);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                data.put("idResep", rs.getString("idResep"));
                data.put("penyakit", rs.getString("penyakit"));
                data.put("tanggal", rs.getString("tanggal"));
                data.put("idPasien", rs.getString("idPasien"));
                data.put("idDokter", rs.getString("idDokter"));
                data.put("idObat", rs.getString("idObat"));
                data.put("status", rs.getString("status")); // tambahkan status
                data.put("namaPasien", rs.getString("namaPasien"));
                data.put("namaDokter", rs.getString("namaDokter"));
                data.put("namaObat", rs.getString("namaObat"));
            }
        }

        return data;
    }
}
