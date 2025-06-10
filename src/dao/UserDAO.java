/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import model.User;
import klinik_pbo.koneksi;

public class UserDAO {

    // Mengambil semua data user dari tabel `user`
    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection conn = koneksi.getKoneksi();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idUser = rs.getString("idUser");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                // Gunakan username sebagai nama default
                User user = new User(idUser, username, username, password, role);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    // Tambahkan user baru ke database
    public static void insertUser(User user) {
        String sql = "INSERT INTO user (idUser, username, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = koneksi.getKoneksi();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getIdUser());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (Opsional) Cek apakah username sudah digunakan
    public static boolean isUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection conn = koneksi.getKoneksi();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}



