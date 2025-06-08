/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Ruangan;
import klinik_pbo.koneksi;
import java.sql.*;
import java.util.*;

public class RuanganDAO {
    public static List<Ruangan> getAllRuangan() {
        List<Ruangan> list = new ArrayList<>();
        Connection conn = koneksi.getKoneksi();

        try {
            String sql = "SELECT * FROM ruangan";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ruangan r = new Ruangan(
                    rs.getString("idRuangan"),
                    rs.getString("namaRuangan"),
                    rs.getInt("lantai"),
                    rs.getInt("kapasitas")
                );
                list.add(r);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public static void insertRuangan(Ruangan r) {
        Connection conn = koneksi.getKoneksi();
        String sql = "INSERT INTO ruangan (idRuangan, namaRuangan, lantai, kapasitas) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, r.getIdRuangan());
            stmt.setString(2, r.getNamaRuangan());
            stmt.setInt(3, r.getLantai());
            stmt.setInt(4, r.getKapasitas());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRuangan(Ruangan r) {
        Connection conn = koneksi.getKoneksi();
        String sql = "UPDATE ruangan SET namaRuangan=?, lantai=?, kapasitas=? WHERE idRuangan=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, r.getNamaRuangan());
            stmt.setInt(2, r.getLantai());
            stmt.setInt(3, r.getKapasitas());
            stmt.setString(4, r.getIdRuangan());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRuangan(String idRuangan) {
        Connection conn = koneksi.getKoneksi();
        String sql = "DELETE FROM ruangan WHERE idRuangan=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idRuangan);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

