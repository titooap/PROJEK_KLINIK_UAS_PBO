/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package klinik_pbo;

import dao.ObatDAO;
import model.Obat;
import klinik_pbo.koneksi; // Make sure this import is correct based on your project structure
import controller.ObatController;
import model.Obat;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author avvjelly
 */
public class DaftarObatAdmin extends javax.swing.JFrame {

    /**
     * Creates new form DaftarObatAdmin
     */
    public DaftarObatAdmin() {
        initComponents();
        setLocationRelativeTo(null); // Center the frame on screen
        // Optional: Set the frame to be maximized horizontally like DashboardAdmin
        setExtendedState(javax.swing.JFrame.MAXIMIZED_HORIZ);
        tampilkanDataObat();
    }

    private void tampilkanDataObat() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID Obat", "Nama Obat", "Stok", "Harga"},
            0
        );

        List<Obat> daftar = ObatDAO.getAllObat();
        for (Obat o : daftar) {
            Object[] row = {
                o.getIdObat(),
                o.getNamaObat(),
                o.getStok(),
                o.getHarga()
            };
            model.addRow(row);
        }
        TabelDaftarObat.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelUtama = new javax.swing.JPanel();
        PanelSidebarAdmin = new javax.swing.JPanel();
        ButtonDashboard = new javax.swing.JButton();
        ButtonDaftarPasien = new javax.swing.JButton();
        ButtonDaftarDokter = new javax.swing.JButton();
        ButtonDaftarObat = new javax.swing.JButton();
        ButtonDaftarRuangan = new javax.swing.JButton();
        ButtonPendaftaranPasien = new javax.swing.JButton();
        ButtonPendaftaranDokter = new javax.swing.JButton();
        ButtonPendaftaranObat = new javax.swing.JButton();
        ButtonJadwalDokter = new javax.swing.JButton();
        ButtonResepPasien = new javax.swing.JButton();
        ButtonLogout = new javax.swing.JButton();
        LabelJudulHalaman = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelDaftarObat = new javax.swing.JTable();
        ButtonEditObat = new javax.swing.JButton();
        ButtonDeleteObat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Obat Admin - Sistem Informasi Klinik");

        PanelUtama.setBackground(new java.awt.Color(240, 240, 240)); // Light grey background
        PanelUtama.setPreferredSize(new java.awt.Dimension(1000, 700)); // Increased size for better layout

        PanelSidebarAdmin.setBackground(new java.awt.Color(0, 51, 102)); // Dark blue
        PanelSidebarAdmin.setPreferredSize(new java.awt.Dimension(220, 700)); // Wider sidebar

        ButtonDashboard.setBackground(new java.awt.Color(0, 51, 102)); // Default sidebar button color
        ButtonDashboard.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // Normal font
        ButtonDashboard.setForeground(new java.awt.Color(255, 255, 255)); // White text
        ButtonDashboard.setText("Dashboard");
        ButtonDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
        ButtonDashboard.setFocusPainted(false); // Remove focus border
        ButtonDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dashboard.png")));
        ButtonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDashboardActionPerformed(evt);
            }
        });

        ButtonDaftarPasien.setBackground(new java.awt.Color(0, 51, 102));
        ButtonDaftarPasien.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonDaftarPasien.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDaftarPasien.setText("Daftar Pasien");
        ButtonDaftarPasien.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonDaftarPasien.setFocusPainted(false);
        ButtonDaftarPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/daftar_pasien.png")));
        ButtonDaftarPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDaftarPasienActionPerformed(evt);
            }
        });

        ButtonDaftarDokter.setBackground(new java.awt.Color(0, 51, 102));
        ButtonDaftarDokter.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonDaftarDokter.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDaftarDokter.setText("Daftar Dokter");
        ButtonDaftarDokter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonDaftarDokter.setFocusPainted(false);
        ButtonDaftarDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/daftar_dokter.png")));
        ButtonDaftarDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDaftarDokterActionPerformed(evt);
            }
        });

        ButtonDaftarObat.setBackground(new java.awt.Color(0, 102, 153)); // Highlight for current page
        ButtonDaftarObat.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 15)); // Bold and larger
        ButtonDaftarObat.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDaftarObat.setText("Daftar Obat");
        ButtonDaftarObat.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonDaftarObat.setFocusPainted(false);
        ButtonDaftarObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/daftar_obat.png")));
        ButtonDaftarObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDaftarObatActionPerformed(evt);
            }
        });

        ButtonDaftarRuangan.setBackground(new java.awt.Color(0, 51, 102));
        ButtonDaftarRuangan.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonDaftarRuangan.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDaftarRuangan.setText("Daftar Ruangan");
        ButtonDaftarRuangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonDaftarRuangan.setFocusPainted(false);
        ButtonDaftarRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ruangan.png")));
        ButtonDaftarRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDaftarRuanganActionPerformed(evt);
            }
        });

        ButtonPendaftaranPasien.setBackground(new java.awt.Color(0, 51, 102));
        ButtonPendaftaranPasien.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonPendaftaranPasien.setForeground(new java.awt.Color(255, 255, 255));
        ButtonPendaftaranPasien.setText("Pendaftaran Pasien");
        ButtonPendaftaranPasien.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonPendaftaranPasien.setFocusPainted(false);
        ButtonPendaftaranPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pendaftaran_pasien.png")));
        ButtonPendaftaranPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPendaftaranPasienActionPerformed(evt);
            }
        });

        ButtonPendaftaranDokter.setBackground(new java.awt.Color(0, 51, 102));
        ButtonPendaftaranDokter.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonPendaftaranDokter.setForeground(new java.awt.Color(255, 255, 255));
        ButtonPendaftaranDokter.setText("Pendaftaran Dokter");
        ButtonPendaftaranDokter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonPendaftaranDokter.setFocusPainted(false);
        ButtonPendaftaranDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pendaftaran_dokter.png")));
        ButtonPendaftaranDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPendaftaranDokterActionPerformed(evt);
            }
        });

        ButtonPendaftaranObat.setBackground(new java.awt.Color(0, 51, 102));
        ButtonPendaftaranObat.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonPendaftaranObat.setForeground(new java.awt.Color(255, 255, 255));
        ButtonPendaftaranObat.setText("Pendaftaran Obat");
        ButtonPendaftaranObat.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonPendaftaranObat.setFocusPainted(false);
        ButtonPendaftaranObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pendaftaran_obat.png")));
        ButtonPendaftaranObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPendaftaranObatActionPerformed(evt);
            }
        });

        ButtonJadwalDokter.setBackground(new java.awt.Color(0, 51, 102));
        ButtonJadwalDokter.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonJadwalDokter.setForeground(new java.awt.Color(255, 255, 255));
        ButtonJadwalDokter.setText("Jadwal Dokter");
        ButtonJadwalDokter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonJadwalDokter.setFocusPainted(false);
        ButtonJadwalDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/jadwal_dokter.png")));
        ButtonJadwalDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonJadwalDokterActionPerformed(evt);
            }
        });

        ButtonResepPasien.setBackground(new java.awt.Color(0, 51, 102));
        ButtonResepPasien.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14));
        ButtonResepPasien.setForeground(new java.awt.Color(255, 255, 255));
        ButtonResepPasien.setText("Resep Pasien");
        ButtonResepPasien.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonResepPasien.setFocusPainted(false);
        ButtonResepPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/resep.png")));
        ButtonResepPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonResepPasienActionPerformed(evt);
            }
        });

        ButtonLogout.setBackground(new java.awt.Color(204, 0, 0)); // Red for logout
        ButtonLogout.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14));
        ButtonLogout.setForeground(new java.awt.Color(255, 255, 255));
        ButtonLogout.setText("Logout");
        ButtonLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        ButtonLogout.setFocusPainted(false);
        ButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png")));
        ButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSidebarAdminLayout = new javax.swing.GroupLayout(PanelSidebarAdmin);
        PanelSidebarAdmin.setLayout(PanelSidebarAdminLayout);
        PanelSidebarAdminLayout.setHorizontalGroup(
            PanelSidebarAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSidebarAdminLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelSidebarAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonResepPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonJadwalDokter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonPendaftaranObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonPendaftaranDokter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonPendaftaranPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDaftarRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDaftarObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDaftarDokter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDaftarPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(ButtonLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PanelSidebarAdminLayout.setVerticalGroup(
            PanelSidebarAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSidebarAdminLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(ButtonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(ButtonDaftarPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDaftarDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDaftarObat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDaftarRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ButtonPendaftaranPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPendaftaranDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPendaftaranObat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonJadwalDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonResepPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE) // Pushed logout to bottom
                .addComponent(ButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        LabelJudulHalaman.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 30)); // Larger font
        LabelJudulHalaman.setForeground(new java.awt.Color(0, 51, 102)); // Dark blue text
        LabelJudulHalaman.setText("Daftar Obat Klinik"); // Specific title for this page

        TabelDaftarObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Obat", "Nama Obat", "Stok", "Harga"
            }
        ));
        TabelDaftarObat.setRowHeight(25); // Increased row height
        TabelDaftarObat.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12)); // Bold header
        jScrollPane1.setViewportView(TabelDaftarObat);

        ButtonEditObat.setBackground(new java.awt.Color(0, 153, 51)); // Green for edit
        ButtonEditObat.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14));
        ButtonEditObat.setForeground(new java.awt.Color(255, 255, 255));
        ButtonEditObat.setText("Edit Obat");
        ButtonEditObat.setFocusPainted(false);
        ButtonEditObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEditObatActionPerformed(evt);
            }
        });

        ButtonDeleteObat.setBackground(new java.awt.Color(204, 0, 0)); // Red for delete
        ButtonDeleteObat.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14));
        ButtonDeleteObat.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDeleteObat.setText("Hapus Obat");
        ButtonDeleteObat.setFocusPainted(false);
        ButtonDeleteObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelUtamaLayout = new javax.swing.GroupLayout(PanelUtama);
        PanelUtama.setLayout(PanelUtamaLayout);
        PanelUtamaLayout.setHorizontalGroup(
            PanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUtamaLayout.createSequentialGroup()
                .addComponent(PanelSidebarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(PanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelJudulHalaman)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelUtamaLayout.createSequentialGroup()
                        .addComponent(ButtonEditObat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonDeleteObat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelUtamaLayout.setVerticalGroup(
            PanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSidebarAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(PanelUtamaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(LabelJudulHalaman)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonEditObat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDeleteObat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE) // Adjusted preferred size
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE) // Adjusted preferred size
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDashboardActionPerformed
        this.dispose();
        DashboardAdmin dashboard = new DashboardAdmin();
        dashboard.setVisible(true);
    }//GEN-LAST:event_ButtonDashboardActionPerformed

    private void ButtonDaftarPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDaftarPasienActionPerformed
        this.dispose();
        DaftarPasienAdmin daftarpasien = new DaftarPasienAdmin();
        daftarpasien.setVisible(true);
    }//GEN-LAST:event_ButtonDaftarPasienActionPerformed

    private void ButtonDaftarDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDaftarDokterActionPerformed
        this.dispose();
        DaftarDokterAdmin daftardokter = new DaftarDokterAdmin();
        daftardokter.setVisible(true);
    }//GEN-LAST:event_ButtonDaftarDokterActionPerformed

    private void ButtonDaftarObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDaftarObatActionPerformed
        // This is the current page, so no action needed, or refresh if desired
        // this.dispose();
        // DaftarObatAdmin daftarobat = new DaftarObatAdmin();
        // daftarobat.setVisible(true);
    }//GEN-LAST:event_ButtonDaftarObatActionPerformed

    private void ButtonDaftarRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDaftarRuanganActionPerformed
        this.dispose();
        DaftarRuanganAdmin daftarruangan = new DaftarRuanganAdmin();
        daftarruangan.setVisible(true);
    }//GEN-LAST:event_ButtonDaftarRuanganActionPerformed

    private void ButtonPendaftaranPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPendaftaranPasienActionPerformed
        this.dispose();
        PendaftaranPasienAdmin pendaftarpasien = new PendaftaranPasienAdmin();
        pendaftarpasien.setVisible(true);
    }//GEN-LAST:event_ButtonPendaftaranPasienActionPerformed

    private void ButtonPendaftaranDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPendaftaranDokterActionPerformed
        this.dispose();
        PendaftaranDokterAdmin daftardokter = new PendaftaranDokterAdmin();
        daftardokter.setVisible(true);
    }//GEN-LAST:event_ButtonPendaftaranDokterActionPerformed

    private void ButtonPendaftaranObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPendaftaranObatActionPerformed
        this.dispose();
        PendaftaranObatAdmin daftarobat = new PendaftaranObatAdmin();
        daftarobat.setVisible(true);
    }//GEN-LAST:event_ButtonPendaftaranObatActionPerformed

    private void ButtonJadwalDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonJadwalDokterActionPerformed
        this.dispose();
        JadwalDokterAdmin daftarjadwal = new JadwalDokterAdmin();
        daftarjadwal.setVisible(true);
    }//GEN-LAST:event_ButtonJadwalDokterActionPerformed

    private void ButtonResepPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonResepPasienActionPerformed
        this.dispose();
        ResepPasienAdmin daftarresep = new ResepPasienAdmin();
        daftarresep.setVisible(true);
    }//GEN-LAST:event_ButtonResepPasienActionPerformed

    private void ButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLogoutActionPerformed
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            this.dispose();
            Login login = new Login();
            login.setVisible(true);
        }
    }//GEN-LAST:event_ButtonLogoutActionPerformed

    private void ButtonEditObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEditObatActionPerformed
        try {
            Connection conn = koneksi.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idObat, namaObat FROM obat");

            JComboBox<String> comboBox = new JComboBox<>();
            Map<String, String> obatMap = new HashMap<>();

            while (rs.next()) {
                String id = rs.getString("idObat");
                String nama = rs.getString("namaObat");
                comboBox.addItem(nama);
                obatMap.put(nama, id);
            }

            if (obatMap.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada obat yang tersedia untuk diedit.");
                return;
            }

            int option = JOptionPane.showConfirmDialog(null, comboBox, "Pilih Obat untuk Diedit", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String namaDipilih = (String) comboBox.getSelectedItem();
                String idDipilih = obatMap.get(namaDipilih);

                PreparedStatement ps = conn.prepareStatement("SELECT * FROM obat WHERE idObat=?");
                ps.setString(1, idDipilih);
                ResultSet detail = ps.executeQuery();

                if (detail.next()) {
                    String namaObat = detail.getString("namaObat");
                    int stok = detail.getInt("stok");
                    double harga = detail.getDouble("harga");

                    JTextField namaField = new JTextField(namaObat);
                    JTextField stokField = new JTextField(String.valueOf(stok));
                    JTextField hargaField = new JTextField(String.valueOf(harga));

                    Object[] fields = {
                        "Nama Obat:", namaField,
                        "Stok:", stokField,
                        "Harga:", hargaField
                    };

                    int edit = JOptionPane.showConfirmDialog(null, fields, "Edit Data Obat", JOptionPane.OK_CANCEL_OPTION);
                    if (edit == JOptionPane.OK_OPTION) {
                        Obat o = new Obat(
                            idDipilih,
                            namaField.getText(),
                            Integer.parseInt(stokField.getText()),
                            Double.parseDouble(hargaField.getText())
                        );
                        ObatDAO.updateObat(o);
                        JOptionPane.showMessageDialog(null, "Data obat berhasil diperbarui.");
                        tampilkanDataObat();
                    }
                }

                detail.close();
                ps.close();
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_ButtonEditObatActionPerformed

    private void ButtonDeleteObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteObatActionPerformed
        try {
            Connection conn = koneksi.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idObat, namaObat FROM obat");

            JComboBox<String> comboBox = new JComboBox<>();
            Map<String, String> obatMap = new HashMap<>();

            while (rs.next()) {
                String id = rs.getString("idObat");
                String nama = rs.getString("namaObat");
                comboBox.addItem(nama);
                obatMap.put(nama, id);
            }

            if (obatMap.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada obat yang tersedia untuk dihapus.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                stmt.close();
                conn.close();
                return;
            }

            int option = JOptionPane.showConfirmDialog(null, comboBox, "Pilih Obat untuk Dihapus", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String namaDipilih = (String) comboBox.getSelectedItem();
                String idDipilih = obatMap.get(namaDipilih);

                int konfirmasi = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus obat \"" + namaDipilih + "\"?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    try {
                        ObatDAO.deleteObat(idDipilih);
                        JOptionPane.showMessageDialog(null, "Obat berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        tampilkanDataObat(); // refresh tabel obat
                    } catch (SQLException delErr) {
                        JOptionPane.showMessageDialog(null, 
                            "Gagal menghapus obat: " + delErr.getMessage(), 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ButtonDeleteObatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DaftarObatAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarObatAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarObatAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarObatAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DaftarObatAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDaftarDokter;
    private javax.swing.JButton ButtonDaftarObat;
    private javax.swing.JButton ButtonDaftarPasien;
    private javax.swing.JButton ButtonDaftarRuangan;
    private javax.swing.JButton ButtonDashboard;
    private javax.swing.JButton ButtonDeleteObat;
    private javax.swing.JButton ButtonEditObat;
    private javax.swing.JButton ButtonJadwalDokter;
    private javax.swing.JButton ButtonLogout;
    private javax.swing.JButton ButtonPendaftaranDokter;
    private javax.swing.JButton ButtonPendaftaranObat;
    private javax.swing.JButton ButtonPendaftaranPasien;
    private javax.swing.JButton ButtonResepPasien;
    private javax.swing.JLabel LabelJudulHalaman;
    private javax.swing.JPanel PanelSidebarAdmin;
    private javax.swing.JPanel PanelUtama;
    private javax.swing.JTable TabelDaftarObat;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}