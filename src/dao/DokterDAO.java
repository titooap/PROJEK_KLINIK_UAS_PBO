/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Dokter;
import klinik_pbo.koneksi;
import java.sql.*;
import java.util.*;

public class DokterDAO {
    public static List<Dokter> getAllDokter() {
        List<Dokter> list = new ArrayList<>();
        Connection conn = koneksi.getKoneksi();

        try {
            String sql = "SELECT * FROM dokter";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dokter d = new Dokter(
                    rs.getString("idDokter"),
                    rs.getString("nama"),
                    rs.getString("spesialisasi"),
                    rs.getString("idUser")
                );
                list.add(d);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public static void insertDokter(Dokter d) throws SQLException {
        Connection conn = koneksi.getKoneksi();

        // Generate ID User otomatis jika kosong
        if (d.getIdUser() == null || d.getIdUser().isEmpty()) {
            String newUserId = generateNewUserId();
            d.setIdUser(newUserId);
        }

        String sql = "INSERT INTO dokter (idDokter, nama, spesialisasi, idUser) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, d.getIdDokter());
        stmt.setString(2, d.getNama());
        stmt.setString(3, d.getSpesialisasi());
        stmt.setString(4, d.getIdUser());
        stmt.executeUpdate();
        stmt.close();
    }

    // Tambahkan method generateNewUserId()
    private static String generateNewUserId() throws SQLException {
        Connection conn = koneksi.getKoneksi();
        String sql = "SELECT CONCAT('USR', LPAD(IFNULL(MAX(CAST(SUBSTRING(idUser, 4) AS UNSIGNED)), 0) + 1, 3, '0')) FROM user";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        String newId = "";
        if(rs.next()) {
            newId = rs.getString(1);
        }
        
        rs.close();
        stmt.close();
        return newId;
    }

    public static void updateDokter(Dokter d) {
        Connection conn = koneksi.getKoneksi();
        String sql = "UPDATE dokter SET nama=?, spesialisasi=?, idUser=? WHERE idDokter=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, d.getNama());
            stmt.setString(2, d.getSpesialisasi());
            stmt.setString(3, d.getIdUser());
            stmt.setString(4, d.getIdDokter());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDokter(String idDokter) throws SQLException {
        Connection conn = koneksi.getKoneksi();
        String sql = "DELETE FROM dokter WHERE idDokter=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idDokter);
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("foreign key")) {
                throw new SQLException("Tidak bisa menghapus dokter karena masih digunakan dalam data resep.");
            } else {
                throw e;
            }
        }
    }
}

