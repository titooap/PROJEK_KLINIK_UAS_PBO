/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klinik_pbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardService {

    public int getJumlahPasien() {
        return getCount("pasien");
    }

    public int getJumlahDokter() {
        return getCount("dokter");
    }

    public int getJumlahRuangan() {
        return getCount("ruangan");
    }

    private int getCount(String tableName) {
        int count = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik_db", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM " + tableName)) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("Error counting from table " + tableName + ": " + e.getMessage());
        }
        return count;
    }
}

