/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.JadwalDokter;
import klinik_pbo.koneksi;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class JadwalDokterDAO {
    private Connection conn;

    public JadwalDokterDAO() {
        this.conn = koneksi.getKoneksi(); // Pastikan class koneksi sudah ada
    }
    
    
    public static List<JadwalDokter> getAllJadwalDokterLengkap() {
    List<JadwalDokter> list = new ArrayList<>();
    Connection conn = koneksi.getKoneksi();

        try {
            String sql = """
                SELECT 
                    jd.idJadwalDokter,
                    d.nama AS namaDokter,
                    j.hari,
                    r.namaRuangan
                FROM jadwaldokter jd
                JOIN dokter d ON jd.idDokter = d.idDokter
                JOIN jadwal j ON jd.idJadwal = j.idJadwal
                JOIN ruangan r ON jd.idRuangan = r.idRuangan
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JadwalDokter jd = new JadwalDokter(
                    rs.getString("idJadwalDokter"),
                    rs.getString("namaDokter"),
                    rs.getString("hari"),
                    rs.getString("namaRuangan")
                );
                list.add(jd);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Method untuk insert jadwal dokter baru
public void insertJadwalDokter(String idDokter, String hari, String jamMulai, 
                              String jamBerakhir, String idRuangan) throws SQLException {
    // Insert ke tabel jadwal dulu
    String insertJadwal = "INSERT INTO jadwal (idJadwal, hari, jamMulai, jamBerakhir) VALUES (?, ?, ?, ?)";
    String idJadwal = "JAD" + System.currentTimeMillis(); // Generate unique ID

    try (PreparedStatement ps = conn.prepareStatement(insertJadwal)) {
        ps.setString(1, idJadwal);
        ps.setString(2, hari);
        ps.setString(3, jamMulai);
        ps.setString(4, jamBerakhir);
        ps.executeUpdate();
    }

    // Insert ke tabel jadwaldokter
    String insertJadwalDokter = "INSERT INTO jadwaldokter (idJadwalDokter, idDokter, idJadwal, idRuangan) VALUES (?, ?, ?, ?)";
    String idJadwalDokter = "JDK" + System.currentTimeMillis(); // Generate unique ID

    try (PreparedStatement ps = conn.prepareStatement(insertJadwalDokter)) {
        ps.setString(1, idJadwalDokter);
        ps.setString(2, idDokter);
        ps.setString(3, idJadwal);
        ps.setString(4, idRuangan);
        ps.executeUpdate();
    }
}

// Method untuk mendapatkan semua data jadwal dokter (untuk refresh tabel)
public List<JadwalDokter> getAllJadwalDokter() throws SQLException {
    List<JadwalDokter> list = new ArrayList<>();
    String query = "SELECT jd.idJadwalDokter, d.nama AS namaDokter, j.hari, r.namaRuangan " +
                   "FROM jadwaldokter jd " +
                   "JOIN dokter d ON jd.idDokter = d.idDokter " +
                   "JOIN jadwal j ON jd.idJadwal = j.idJadwal " +
                   "JOIN ruangan r ON jd.idRuangan = r.idRuangan";

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            list.add(new JadwalDokter(
                rs.getString("idJadwalDokter"),
                rs.getString("namaDokter"),
                rs.getString("hari"),
                rs.getString("namaRuangan")
            ));
        }
    }
    return list;
}

    // Ambil semua jadwal dokter untuk ditampilkan di combo box
    public Map<String, String> getAllJadwalDokterForCombo() throws SQLException {
        Map<String, String> jadwalMap = new HashMap<>();
        String query = "SELECT jd.idJadwalDokter, d.nama AS namaDokter, j.hari, r.namaRuangan " +
                       "FROM jadwaldokter jd " +
                       "JOIN dokter d ON jd.idDokter = d.idDokter " +
                       "JOIN jadwal j ON jd.idJadwal = j.idJadwal " +
                       "JOIN ruangan r ON jd.idRuangan = r.idRuangan";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("idJadwalDokter");
                String displayText = rs.getString("namaDokter") + " - " + 
                                   rs.getString("hari") + " - " + 
                                   rs.getString("namaRuangan");
                jadwalMap.put(displayText, id);
            }
        }
        return jadwalMap;
    }

    // Ambil detail jadwal dokter by ID
    public Map<String, String> getJadwalDokterById(String idJadwalDokter) throws SQLException {
        Map<String, String> data = new HashMap<>();
        String query = "SELECT jd.*, d.nama AS namaDokter, j.hari, j.jamMulai, j.jamBerakhir, r.namaRuangan " +
                       "FROM jadwaldokter jd " +
                       "JOIN dokter d ON jd.idDokter = d.idDokter " +
                       "JOIN jadwal j ON jd.idJadwal = j.idJadwal " +
                       "JOIN ruangan r ON jd.idRuangan = r.idRuangan " +
                       "WHERE jd.idJadwalDokter = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, idJadwalDokter);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                data.put("idJadwalDokter", rs.getString("idJadwalDokter"));
                data.put("idDokter", rs.getString("idDokter"));
                data.put("namaDokter", rs.getString("namaDokter"));
                data.put("idJadwal", rs.getString("idJadwal"));
                data.put("hari", rs.getString("hari"));
                data.put("jamMulai", rs.getString("jamMulai"));
                data.put("jamBerakhir", rs.getString("jamBerakhir"));
                data.put("idRuangan", rs.getString("idRuangan"));
                data.put("namaRuangan", rs.getString("namaRuangan"));
            }
        }
        return data;
    }

    // Update jadwal dokter
    public void updateJadwalDokter(String idJadwalDokter, String idDokter, String hari, 
                                  String jamMulai, String jamBerakhir, String idRuangan) throws SQLException {
        // Pertama dapatkan idJadwal dari jadwaldokter
        String idJadwal = null;
        String queryGetJadwal = "SELECT idJadwal FROM jadwaldokter WHERE idJadwalDokter = ?";
        try (PreparedStatement ps = conn.prepareStatement(queryGetJadwal)) {
            ps.setString(1, idJadwalDokter);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idJadwal = rs.getString("idJadwal");
            }
        }

        if (idJadwal != null) {
            // Update tabel jadwal
            String queryUpdateJadwal = "UPDATE jadwal SET hari=?, jamMulai=?, jamBerakhir=? WHERE idJadwal=?";
            try (PreparedStatement ps = conn.prepareStatement(queryUpdateJadwal)) {
                ps.setString(1, hari);
                ps.setString(2, jamMulai);
                ps.setString(3, jamBerakhir);
                ps.setString(4, idJadwal);
                ps.executeUpdate();
            }

            // Update tabel jadwaldokter
            String queryUpdateJadwalDokter = "UPDATE jadwaldokter SET idDokter=?, idRuangan=? WHERE idJadwalDokter=?";
            try (PreparedStatement ps = conn.prepareStatement(queryUpdateJadwalDokter)) {
                ps.setString(1, idDokter);
                ps.setString(2, idRuangan);
                ps.setString(3, idJadwalDokter);
                ps.executeUpdate();
            }
        }
    }

    // Hapus jadwal dokter
    public void deleteJadwalDokter(String idJadwalDokter) throws SQLException {
        // Pertama dapatkan idJadwal untuk dihapus dari tabel jadwal
        String idJadwal = null;
        String queryGetJadwal = "SELECT idJadwal FROM jadwaldokter WHERE idJadwalDokter = ?";
        try (PreparedStatement ps = conn.prepareStatement(queryGetJadwal)) {
            ps.setString(1, idJadwalDokter);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idJadwal = rs.getString("idJadwal");
            }
        }

        if (idJadwal != null) {
            // Hapus dari jadwaldokter terlebih dahulu
            String queryDeleteJadwalDokter = "DELETE FROM jadwaldokter WHERE idJadwalDokter = ?";
            try (PreparedStatement ps = conn.prepareStatement(queryDeleteJadwalDokter)) {
                ps.setString(1, idJadwalDokter);
                ps.executeUpdate();
            }

            // Kemudian hapus dari jadwal
            String queryDeleteJadwal = "DELETE FROM jadwal WHERE idJadwal = ?";
            try (PreparedStatement ps = conn.prepareStatement(queryDeleteJadwal)) {
                ps.setString(1, idJadwal);
                ps.executeUpdate();
            }
        }
    }

    // Ambil daftar dokter untuk combo box
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

    // Ambil daftar ruangan untuk combo box
    public Map<String, String> getAllRuanganForCombo() throws SQLException {
        Map<String, String> ruanganMap = new HashMap<>();
        String query = "SELECT idRuangan, namaRuangan FROM ruangan";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ruanganMap.put(rs.getString("namaRuangan"), rs.getString("idRuangan"));
            }
        }
        return ruanganMap;
    }
    
    public void insertJadwalDokter(String idJadwal, String idDokter, String hari, 
                              String jamMulai, String jamBerakhir, String idRuangan) throws SQLException {
    // Insert ke tabel jadwal
    String insertJadwal = "INSERT INTO jadwal (idJadwal, hari, jamMulai, jamBerakhir) VALUES (?, ?, ?, ?)";
    
    try (PreparedStatement ps = conn.prepareStatement(insertJadwal)) {
        ps.setString(1, idJadwal);
        ps.setString(2, hari);
        ps.setString(3, jamMulai);
        ps.setString(4, jamBerakhir);
        ps.executeUpdate();
    }

    // Insert ke tabel jadwaldokter
    String insertJadwalDokter = "INSERT INTO jadwaldokter (idJadwalDokter, idDokter, idJadwal, idRuangan) VALUES (?, ?, ?, ?)";
    String idJadwalDokter = idJadwal; // Generate ID jadwal dokter
    
    try (PreparedStatement ps = conn.prepareStatement(insertJadwalDokter)) {
        ps.setString(1, idJadwalDokter);
        ps.setString(2, idDokter);
        ps.setString(3, idJadwal);
        ps.setString(4, idRuangan);
        ps.executeUpdate();
    }
}
}