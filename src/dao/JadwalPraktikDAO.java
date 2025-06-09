/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import model.JadwalPraktik;

public class JadwalPraktikDAO {
    private Connection conn;

    public JadwalPraktikDAO(Connection conn) {
        this.conn = conn;
    }

    public List<JadwalPraktik> getAllJadwal() throws SQLException {
        List<JadwalPraktik> list = new ArrayList<>();
        String query = """
            SELECT j.idJadwal, j.hari, j.jamMulai, j.jamBerakhir
            FROM jadwal j
        """;

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new JadwalPraktik(
                rs.getString("idJadwal"),
                rs.getString("hari"),
                rs.getString("jamMulai"),
                rs.getString("jamBerakhir")
));
            }
        }
        return list;
    }
}

