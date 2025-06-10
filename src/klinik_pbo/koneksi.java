package klinik_pbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection conn;
    
    // Konfigurasi database (sesuaikan dengan setting database Anda)
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/klinik_db";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // Method untuk mendapatkan koneksi
    public static Connection getKoneksi() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Koneksi ke database berhasil!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Koneksi ke database gagal: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }

    
    // Method untuk menutup koneksi
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Koneksi database ditutup.");
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}