package controller;

import dao.ResepPasienDAO;
import model.ResepPasien;
import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

/**
 * Controller untuk manajemen resep pasien.
 * Menangani operasi CRUD resep dengan antarmuka berbasis role (admin/dokter).
 */
public class ResepPasienController {
    private final ResepPasienDAO dao;
    private final String role; // admin / dokter

    /**
     * Konstruktor controller.
     * @param role Role pengguna (admin/dokter) untuk menentukan aksi yang tersedia
     */
    public ResepPasienController(String role) {
        this.dao = new ResepPasienDAO();
        this.role = role;
    }

    /**
     * Menampilkan dialog aksi berdasarkan role pengguna.
     */
    public void showActionDialog() {
        if (role.equals("admin")) {
            showAdminActions();
        } else {
            showDokterActions();
        }
    }

    private void showAdminActions() {
        Object[] options = {"Ubah Status", "Hapus", "Batal"};
        int pilihan = JOptionPane.showOptionDialog(null,
                "Pilih Aksi Admin",
                "Manajemen Resep (Admin)",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (pilihan) {
            case 0 -> showAdminStatusUpdateDialog();
            case 1 -> hapusResep();
        }
    }

    private void showDokterActions() {
        Object[] options = {"Insert", "Edit", "Batal"};
        int pilihan = JOptionPane.showOptionDialog(null,
                "Pilih Aksi untuk Resep Pasien",
                "Manajemen Resep (Dokter)",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (pilihan) {
            case 0 -> tambahResep();
            case 1 -> editResep();
        }
    }

    /**
     * Menampilkan dialog untuk mengubah status resep (admin only).
     */
    private void showAdminStatusUpdateDialog() {
        try {
            List<ResepPasien> resepList = dao.getAllResepLengkap();
            if (resepList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada resep untuk diubah status.");
                return;
            }

            Map<String, String> resepMap = createResepSelectionMap(resepList);
            String idResep = showSelectionDialog(resepMap, "Pilih Resep");
            if (idResep == null) return;

            Map<String, String> data = dao.getResepById(idResep);
            String newStatus = showStatusUpdateDialog(data.get("status"));
            if (newStatus != null) {
                dao.updateStatusResep(idResep, newStatus);
                JOptionPane.showMessageDialog(null, "Status berhasil diperbarui.");
                refreshTable();
            }
        } catch (Exception e) {
            handleError("Update Status Error", e);
        }
    }

    /**
     * Menambahkan resep baru (dokter only).
     */
    private void tambahResep() {
        try {
            // Ambil data dari database
            Map<String, String> pasienMap = dao.getAllPasienForCombo();
            Map<String, String> dokterMap = dao.getAllDokterForCombo();
            Map<String, String> obatMap = dao.getAllObatForCombo();

            // Cek jika data pasien/dokter/obat kosong
            if (pasienMap.isEmpty() || dokterMap.isEmpty() || obatMap.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Data pasien, dokter, atau obat tidak tersedia.\nPastikan semua data sudah dimasukkan.", 
                    "Data Tidak Lengkap", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buat input form
            JTextField idResepField = new JTextField();
            JComboBox<String> pasienCombo = new JComboBox<>(pasienMap.keySet().toArray(new String[0]));
            JComboBox<String> dokterCombo = new JComboBox<>(dokterMap.keySet().toArray(new String[0]));
            JComboBox<String> obatCombo = new JComboBox<>(obatMap.keySet().toArray(new String[0]));
            JTextField penyakitField = new JTextField();
            JSpinner tanggalSpinner = createDateSpinner();

            // Tampilkan dialog input
            if (showResepInputDialog(idResepField, pasienCombo, dokterCombo, obatCombo, penyakitField, tanggalSpinner)) {
                // Validasi ID Resep
                String idResep = idResepField.getText().trim();
                if (idResep.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "ID Resep tidak boleh kosong.", 
                        "Validasi Gagal", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Validasi Penyakit
                if (penyakitField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Nama penyakit harus diisi.", 
                        "Validasi Gagal", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Ambil data
                ResepData resepData = collectResepData(pasienMap, dokterMap, obatMap, 
                    pasienCombo, dokterCombo, obatCombo, penyakitField, tanggalSpinner);

                // Insert ke database
                dao.insertResep(idResep, resepData.penyakit, resepData.tanggal, 
                    resepData.idPasien, resepData.idDokter, resepData.idObat, "belum ditebus");

                JOptionPane.showMessageDialog(null, "Resep berhasil ditambahkan.");
                refreshTable();
            }

        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, 
                "Terjadi kesalahan saat menyimpan data ke database.\n" + sqlEx.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            sqlEx.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Terjadi kesalahan tidak terduga.\n" + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Mengedit resep yang sudah ada (dokter only).
     */
    private void editResep() {
        try {
            List<ResepPasien> resepList = dao.getAllResepLengkap();
            if (resepList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada resep yang tersedia untuk diedit.");
                return;
            }

            Map<String, String> resepMap = createResepSelectionMap(resepList);
            String idResep = showSelectionDialog(resepMap, "Pilih Resep untuk Diedit");
            if (idResep == null) return;

            Map<String, String> data = dao.getResepById(idResep);
            if (showResepEditDialog(data, idResep)) {
                refreshTable();
            }
        } catch (Exception e) {
            handleError("Edit Error", e);
        }
    }

    /**
     * Menghapus resep (admin only).
     */
    private void hapusResep() {
        try {
            List<ResepPasien> list = dao.getAllResepLengkap();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada resep yang tersedia.");
                return;
            }

            Map<String, String> resepMap = createResepSelectionMap(list);
            String idResep = showSelectionDialog(resepMap, "Pilih Resep untuk Dihapus");
            if (idResep == null) return;

            if (confirmDelete()) {
                dao.deleteResep(idResep);
                JOptionPane.showMessageDialog(null, "Resep berhasil dihapus.");
                refreshTable();
            }
        } catch (Exception e) {
            handleError("Delete Error", e);
        }
    }

    // Helper methods
    private Map<String, String> createResepSelectionMap(List<ResepPasien> resepList) {
        Map<String, String> map = new LinkedHashMap<>();
        for (ResepPasien r : resepList) {
            String label = r.getNamaPasien() + " - " + r.getNamaDokter() + " (" + r.getIdResep() + ")";
            map.put(label, r.getIdResep());
        }
        return map;
    }

    private String showSelectionDialog(Map<String, String> map, String title) {
        JComboBox<String> combo = new JComboBox<>(map.keySet().toArray(new String[0]));
        int result = JOptionPane.showConfirmDialog(null, combo, title, JOptionPane.OK_CANCEL_OPTION);
        return (result == JOptionPane.OK_OPTION) ? map.get(combo.getSelectedItem()) : null;
    }

    private String showStatusUpdateDialog(String currentStatus) {
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"belum ditebus", "ditebus"});
        statusCombo.setSelectedItem(currentStatus);

        Object[] field = {"Status:", statusCombo};
        int konfirmasi = JOptionPane.showConfirmDialog(null, field, "Ubah Status Resep", JOptionPane.OK_CANCEL_OPTION);
        return (konfirmasi == JOptionPane.OK_OPTION) ? (String) statusCombo.getSelectedItem() : null;
    }

    private JSpinner createDateSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
        return spinner;
    }

    private boolean showResepInputDialog(JTextField idField, JComboBox<String> pasienCombo, 
            JComboBox<String> dokterCombo, JComboBox<String> obatCombo, 
            JTextField penyakitField, JSpinner tanggalSpinner) {
        
        Object[] fields = {
            "ID Resep:", idField,
            "Pasien:", pasienCombo,
            "Dokter:", dokterCombo,
            "Obat:", obatCombo,
            "Penyakit:", penyakitField,
            "Tanggal:", tanggalSpinner
        };

        return JOptionPane.showConfirmDialog(null, fields, "Tambah Resep", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
    }

    private String validateResepId(String id) {
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Resep wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return id;
    }

    private ResepData collectResepData(Map<String, String> pasienMap, Map<String, String> dokterMap,
            Map<String, String> obatMap, JComboBox<String> pasienCombo, 
            JComboBox<String> dokterCombo, JComboBox<String> obatCombo,
            JTextField penyakitField, JSpinner tanggalSpinner) {
        
        ResepData data = new ResepData();
        data.idPasien = pasienMap.get(pasienCombo.getSelectedItem());
        data.idDokter = dokterMap.get(dokterCombo.getSelectedItem());
        data.idObat = obatMap.get(obatCombo.getSelectedItem());
        data.penyakit = penyakitField.getText();
        data.tanggal = new Date(((java.util.Date) tanggalSpinner.getValue()).getTime());
        return data;
    }

    private boolean showResepEditDialog(Map<String, String> data, String idResep) throws Exception {
        // Prepare form components with existing data
        Map<String, String> pasienMap = dao.getAllPasienForCombo();
        Map<String, String> dokterMap = dao.getAllDokterForCombo();
        Map<String, String> obatMap = dao.getAllObatForCombo();

        JComboBox<String> pasienCombo = new JComboBox<>(pasienMap.keySet().toArray(new String[0]));
        pasienCombo.setSelectedItem(data.get("namaPasien"));

        JComboBox<String> dokterCombo = new JComboBox<>(dokterMap.keySet().toArray(new String[0]));
        dokterCombo.setSelectedItem(data.get("namaDokter"));

        JComboBox<String> obatCombo = new JComboBox<>(obatMap.keySet().toArray(new String[0]));
        obatCombo.setSelectedItem(data.get("namaObat"));

        JTextField penyakitField = new JTextField(data.get("penyakit"));
        JSpinner tanggalSpinner = createDateSpinner();
        tanggalSpinner.setValue(Date.valueOf(data.get("tanggal")));

        // Show edit dialog
        Object[] fields = {
            "Pasien:", pasienCombo,
            "Dokter:", dokterCombo,
            "Obat:", obatCombo,
            "Penyakit:", penyakitField,
            "Tanggal:", tanggalSpinner
        };

        if (JOptionPane.showConfirmDialog(null, fields, "Edit Resep", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            ResepData resepData = collectResepData(pasienMap, dokterMap, obatMap, 
                pasienCombo, dokterCombo, obatCombo, penyakitField, tanggalSpinner);
            
            dao.updateResepTanpaStatus(idResep, resepData.penyakit, resepData.tanggal, 
                resepData.idPasien, resepData.idDokter, resepData.idObat);
            
            JOptionPane.showMessageDialog(null, "Resep berhasil diperbarui.");
            return true;
        }
        return false;
    }

    private boolean confirmDelete() {
        return JOptionPane.showConfirmDialog(null, 
            "Yakin ingin menghapus resep ini?", "Konfirmasi", 
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void refreshTable() {
        // Implementasi refresh tabel view
    }

    private void handleError(String title, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }

    // Helper class untuk mengelompokkan data resep
    private static class ResepData {
        String idPasien;
        String idDokter;
        String idObat;
        String penyakit;
        Date tanggal;
    }
}