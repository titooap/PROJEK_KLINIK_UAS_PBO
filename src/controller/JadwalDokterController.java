package controller;

import java.sql.SQLException;
import dao.JadwalDokterDAO;
import java.util.List;
import model.JadwalDokter;
import javax.swing.*;
import java.util.Map;
import klinik_pbo.JadwalDokterAdmin;

/**
 * Controller untuk manajemen jadwal dokter.
 * Menangani logika bisnis terkait operasi CRUD jadwal dokter.
 */
public class JadwalDokterController {
    private JadwalDokterDAO dao;

    public JadwalDokterController() {
        this.dao = new JadwalDokterDAO();
    }

    /**
     * Menampilkan dialog pilihan aksi untuk jadwal dokter.
     */
    public void showActionDialog() {
        Object[] options = {"Insert", "Edit", "Delete", "Batal"};
        int pilihan = JOptionPane.showOptionDialog(null, 
                "Pilih Aksi untuk Jadwal Dokter", 
                "Manajemen Jadwal Dokter", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);

        switch (pilihan) {
            case 0: tambahJadwalDokter(); break;
            case 1: editJadwalDokter(); break;
            case 2: hapusJadwalDokter(); break;
            default: break;
        }
    }
    
    /**
     * Menambahkan jadwal dokter baru.
     * Menampilkan form input dan memvalidasi data sebelum disimpan.
     */
    private void tambahJadwalDokter() {
        try {
            // Buat form input
            Map<String, String> dokterMap = dao.getAllDokterForCombo();
            JComboBox<String> dokterCombo = new JComboBox<>(dokterMap.keySet().toArray(new String[0]));
            
            JTextField idJadwalField = new JTextField();
            JComboBox<String> hariCombo = new JComboBox<>(new String[]{"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"});
            JTextField jamMulaiField = new JTextField("08:00:00");
            JTextField jamBerakhirField = new JTextField("16:00:00");
            
            Map<String, String> ruanganMap = dao.getAllRuanganForCombo();
            JComboBox<String> ruanganCombo = new JComboBox<>(ruanganMap.keySet().toArray(new String[0]));

            Object[] fields = {
                "ID Jadwal:", idJadwalField,
                "Dokter:", dokterCombo,
                "Hari:", hariCombo,
                "Jam Mulai (HH:MM:SS):", jamMulaiField,
                "Jam Berakhir (HH:MM:SS):", jamBerakhirField,
                "Ruangan:", ruanganCombo
            };

            int result = JOptionPane.showConfirmDialog(null, fields, "Tambah Jadwal Dokter", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                String idJadwal = idJadwalField.getText().trim();
                String idDokter = dokterMap.get(dokterCombo.getSelectedItem());
                String hari = (String) hariCombo.getSelectedItem();
                String jamMulai = jamMulaiField.getText();
                String jamBerakhir = jamBerakhirField.getText();
                String idRuangan = ruanganMap.get(ruanganCombo.getSelectedItem());

                if(idJadwal.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID Jadwal tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dao.insertJadwalDokter(idJadwal, idDokter, hari, jamMulai, jamBerakhir, idRuangan);
                JOptionPane.showMessageDialog(null, "Jadwal dokter berhasil ditambahkan!");
                refreshTable();
            }
        } catch (SQLException e) {
            handleError("Database Error", e);
        }
    }

    /**
     * Mengedit jadwal dokter yang sudah ada.
     * Menampilkan form edit dengan data yang sudah terisi.
     */
    private void editJadwalDokter() {
        try {
            Map<String, String> jadwalMap = dao.getAllJadwalDokterForCombo();

            if (jadwalMap.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada jadwal dokter yang tersedia untuk diedit.");
                return;
            }

            JComboBox<String> comboBox = new JComboBox<>(jadwalMap.keySet().toArray(new String[0]));
            int option = JOptionPane.showConfirmDialog(null, comboBox, "Pilih Jadwal Dokter untuk Diedit", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String displayDipilih = (String) comboBox.getSelectedItem();
                String idDipilih = jadwalMap.get(displayDipilih);

                Map<String, String> data = dao.getJadwalDokterById(idDipilih);
                if (!data.isEmpty()) {
                    showEditForm(data);
                }
            }
        } catch (SQLException e) {
            handleError("Database Error", e);
        }
    }

    /**
     * Menampilkan form edit dengan data yang sudah terisi.
     * @param data Map berisi data jadwal dokter yang akan diedit
     */
    private void showEditForm(Map<String, String> data) {
        try {
            Map<String, String> dokterMap = dao.getAllDokterForCombo();
            JComboBox<String> dokterCombo = new JComboBox<>(dokterMap.keySet().toArray(new String[0]));
            dokterCombo.setSelectedItem(data.get("namaDokter"));

            JComboBox<String> hariCombo = new JComboBox<>(new String[]{"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"});
            hariCombo.setSelectedItem(data.get("hari"));

            JTextField jamMulaiField = new JTextField(data.get("jamMulai"));
            JTextField jamBerakhirField = new JTextField(data.get("jamBerakhir"));

            Map<String, String> ruanganMap = dao.getAllRuanganForCombo();
            JComboBox<String> ruanganCombo = new JComboBox<>(ruanganMap.keySet().toArray(new String[0]));
            ruanganCombo.setSelectedItem(data.get("namaRuangan"));

            Object[] fields = {
                "Dokter:", dokterCombo,
                "Hari:", hariCombo,
                "Jam Mulai (HH:MM:SS):", jamMulaiField,
                "Jam Berakhir (HH:MM:SS):", jamBerakhirField,
                "Ruangan:", ruanganCombo
            };

            int edit = JOptionPane.showConfirmDialog(null, fields, "Edit Jadwal Dokter", JOptionPane.OK_CANCEL_OPTION);
            if (edit == JOptionPane.OK_OPTION) {
                String idDokter = dokterMap.get(dokterCombo.getSelectedItem());
                String hari = (String) hariCombo.getSelectedItem();
                String jamMulai = jamMulaiField.getText();
                String jamBerakhir = jamBerakhirField.getText();
                String idRuangan = ruanganMap.get(ruanganCombo.getSelectedItem());

                dao.updateJadwalDokter(
                    data.get("idJadwalDokter"),
                    idDokter,
                    hari,
                    jamMulai,
                    jamBerakhir,
                    idRuangan
                );

                JOptionPane.showMessageDialog(null, "Jadwal dokter berhasil diperbarui.");
                refreshTable();
            }
        } catch (SQLException e) {
            handleError("Database Error", e);
        }
    }

    /**
     * Menghapus jadwal dokter.
     * Meminta konfirmasi sebelum menghapus data.
     */
    private void hapusJadwalDokter() {
        try {
            Map<String, String> jadwalMap = dao.getAllJadwalDokterForCombo();

            if (jadwalMap.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada jadwal dokter yang tersedia untuk dihapus.");
                return;
            }

            JComboBox<String> comboBox = new JComboBox<>(jadwalMap.keySet().toArray(new String[0]));
            int option = JOptionPane.showConfirmDialog(null, comboBox, "Pilih Jadwal Dokter untuk Dihapus", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String displayDipilih = (String) comboBox.getSelectedItem();
                String idDipilih = jadwalMap.get(displayDipilih);

                int konfirmasi = JOptionPane.showConfirmDialog(null, 
                    "Yakin ingin menghapus jadwal dokter \"" + displayDipilih + "\"?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION);

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    dao.deleteJadwalDokter(idDipilih);
                    JOptionPane.showMessageDialog(null, "Jadwal dokter berhasil dihapus.");
                    refreshTable();
                }
            }
        } catch (SQLException e) {
            handleError("Database Error", e);
        }
    }

    /**
     * Memperbarui tampilan tabel jadwal dokter.
     */
    private void refreshTable() {
        JadwalDokterAdmin adminView = new JadwalDokterAdmin();
        adminView.tampilkanDataJadwalDokter();
    }

    /**
     * Menangani dan menampilkan pesan error.
     * @param title Judul error
     * @param e Exception yang terjadi
     */
    private void handleError(String title, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }
}