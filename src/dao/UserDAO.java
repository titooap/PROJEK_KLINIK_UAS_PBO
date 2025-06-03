/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.*;
import java.util.*;
import klinik_pbo.koneksi;

public class UserDAO {
    public static List<String> getAllIdUser() {
        List<String> idUserList = new ArrayList<>();
        String sql = "SELECT idUser FROM user";

        try (Connection conn = koneksi.getKoneksi();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                idUserList.add(rs.getString("idUser"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idUserList;
    }
}

