package dao;

import model.Pasien;
import klinik_pbo.koneksi;
import java.sql.*;
import java.util.*;

public class PasienDAO {

    // READ
    public static List<Pasien> getAllPasien() {
        List<Pasien> list = new ArrayList<>();
        Connection conn = koneksi.getKoneksi();

        try {
            String sql = "SELECT * FROM pasien";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pasien p = new Pasien(
                    rs.getString("idPasien"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("telepon")
                );
                list.add(p);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // CREATE
    public static void insertPasien(Pasien p) throws SQLException {
        Connection conn = koneksi.getKoneksi();
        String sql = "INSERT INTO pasien (idPasien, nama, alamat, telepon) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, p.getIdPasien());
        stmt.setString(2, p.getNama());
        stmt.setString(3, p.getAlamat());
        stmt.setString(4, p.getTelepon());
        stmt.executeUpdate();
        stmt.close();
    }

    // UPDATE
    public static void updatePasien(Pasien p) {
        Connection conn = koneksi.getKoneksi();
        String sql = "UPDATE pasien SET nama=?, alamat=?, telepon=? WHERE idPasien=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getTelepon());
            stmt.setString(4, p.getIdPasien());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // DELETE
    public static void deletePasien(String idPasien) throws SQLException {
        Connection conn = koneksi.getKoneksi();
        String sql = "DELETE FROM pasien WHERE idPasien=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPasien);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Pasien tidak ditemukan atau tidak bisa dihapus.");
            }
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("foreign key")) {
                throw new SQLException("Gagal menghapus pasien. Pasien ini masih digunakan dalam data resep.");
            } else {
                throw e; // lempar kembali untuk ditangkap di atas
            }
        }
    }
}
