/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Obat;
import klinik_pbo.koneksi;
import java.sql.*;
import java.util.*;

public class ObatDAO {
    public static List<Obat> getAllObat() {
        List<Obat> list = new ArrayList<>();
        Connection conn = koneksi.getKoneksi();

        try {
            String sql = "SELECT * FROM obat";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Obat o = new Obat(
                    rs.getString("idObat"),
                    rs.getString("namaObat"),
                    rs.getInt("stok"),
                    rs.getDouble("harga")
                );
                list.add(o);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
     public static void insertObat(Obat o) {
        Connection conn = koneksi.getKoneksi();
        String sql = "INSERT INTO obat (idObat, namaObat, stok, harga) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, o.getIdObat());
            stmt.setString(2, o.getNamaObat());
            stmt.setInt(3, o.getStok());
            stmt.setDouble(4, o.getHarga());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateObat(Obat o) {
        Connection conn = koneksi.getKoneksi();
        String sql = "UPDATE obat SET namaObat=?, stok=?, harga=? WHERE idObat=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, o.getNamaObat());
            stmt.setInt(2, o.getStok());
            stmt.setDouble(3, o.getHarga());
            stmt.setString(4, o.getIdObat());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteObat(String idObat) {
        Connection conn = koneksi.getKoneksi();
        String sql = "DELETE FROM obat WHERE idObat=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idObat);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

