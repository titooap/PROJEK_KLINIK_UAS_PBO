package controller;

import dao.ResepPasienDAO;
import model.ResepPasien;

import javax.swing.*;
import java.sql.Date;
import java.util.*;

public class ResepPasienController {
    private final ResepPasienDAO dao;
    private final String role; // admin / dokter

    public ResepPasienController(String role) {
        this.dao = new ResepPasienDAO();
        this.role = role;
    }

    public void showActionDialog() {
    if (role.equals("admin")) {
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
    } else {
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
}
    
    private void showAdminStatusUpdateDialog() {
    try {
        List<ResepPasien> resepList = dao.getAllResepLengkap();
        if (resepList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada resep untuk diubah status.");
            return;
        }

        Map<String, String> resepMap = new LinkedHashMap<>();
        for (ResepPasien r : resepList) {
            String label = r.getNamaPasien() + " - " + r.getNamaDokter() + " (" + r.getIdResep() + ")";
            resepMap.put(label, r.getIdResep());
        }

        JComboBox<String> combo = new JComboBox<>(resepMap.keySet().toArray(new String[0]));
        int result = JOptionPane.showConfirmDialog(null, combo, "Pilih Resep", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        String selectedLabel = (String) combo.getSelectedItem();
        String idResep = resepMap.get(selectedLabel);

        Map<String, String> data = dao.getResepById(idResep);
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"belum ditebus", "ditebus"});
        statusCombo.setSelectedItem(data.get("status"));

        Object[] field = {"Status:", statusCombo};
        int konfirmasi = JOptionPane.showConfirmDialog(null, field, "Ubah Status Resep", JOptionPane.OK_CANCEL_OPTION);

        if (konfirmasi == JOptionPane.OK_OPTION) {
            String newStatus = (String) statusCombo.getSelectedItem();
            dao.updateStatusResep(idResep, newStatus);
            JOptionPane.showMessageDialog(null, "Status berhasil diperbarui.");
            refreshTable();
        }

    } catch (Exception e) {
        handleError("Update Status Error", e);
    }
}



    private void tambahResep() {
        try {
            Map<String, String> pasienMap = dao.getAllPasienForCombo();
            Map<String, String> dokterMap = dao.getAllDokterForCombo();
            Map<String, String> obatMap = dao.getAllObatForCombo();

            JTextField idResepField = new JTextField();
            JComboBox<String> pasienCombo = new JComboBox<>(pasienMap.keySet().toArray(new String[0]));
            JComboBox<String> dokterCombo = new JComboBox<>(dokterMap.keySet().toArray(new String[0]));
            JComboBox<String> obatCombo = new JComboBox<>(obatMap.keySet().toArray(new String[0]));
            JTextField penyakitField = new JTextField();
            JSpinner tanggalSpinner = new JSpinner(new SpinnerDateModel());
            tanggalSpinner.setEditor(new JSpinner.DateEditor(tanggalSpinner, "yyyy-MM-dd"));
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"belum ditebus", "ditebus"});

            Object[] fields;
            if (role.equals("admin")) {
                fields = new Object[]{
                        "ID Resep:", idResepField,
                        "Pasien:", pasienCombo,
                        "Dokter:", dokterCombo,
                        "Obat:", obatCombo,
                        "Penyakit:", penyakitField,
                        "Tanggal:", tanggalSpinner,
                        "Status:", statusCombo
                };
            } else {
                fields = new Object[]{
                        "ID Resep:", idResepField,
                        "Pasien:", pasienCombo,
                        "Dokter:", dokterCombo,
                        "Obat:", obatCombo,
                        "Penyakit:", penyakitField,
                        "Tanggal:", tanggalSpinner
                };
            }

            int result = JOptionPane.showConfirmDialog(null, fields, "Tambah Resep", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String idResep = idResepField.getText().trim();
                if (idResep.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID Resep wajib diisi!");
                    return;
                }

                String idPasien = pasienMap.get(pasienCombo.getSelectedItem());
                String idDokter = dokterMap.get(dokterCombo.getSelectedItem());
                String idObat = obatMap.get(obatCombo.getSelectedItem());
                String penyakit = penyakitField.getText();
                Date sqlDate = new Date(((java.util.Date) tanggalSpinner.getValue()).getTime());
                String status = role.equals("admin") ? (String) statusCombo.getSelectedItem() : "belum ditebus";

                dao.insertResep(idResep, penyakit, sqlDate, idPasien, idDokter, idObat, status);
                JOptionPane.showMessageDialog(null, "Resep berhasil ditambahkan.");
                refreshTable();
            }
        } catch (Exception e) {
            handleError("Insert Error", e);
        }
    }

    private void editResep() {
        try {
            List<ResepPasien> resepList = dao.getAllResepLengkap();
            if (resepList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada resep yang tersedia untuk diedit.");
                return;
            }

            Map<String, String> resepMap = new LinkedHashMap<>();
            for (ResepPasien r : resepList) {
                String label = r.getNamaPasien() + " - " + r.getNamaDokter() + " (" + r.getIdResep() + ")";
                resepMap.put(label, r.getIdResep());
            }

            JComboBox<String> combo = new JComboBox<>(resepMap.keySet().toArray(new String[0]));
            int result = JOptionPane.showConfirmDialog(null, combo, "Pilih Resep untuk Diedit", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            String selectedLabel = combo.getSelectedItem().toString();
            String idResep = resepMap.get(selectedLabel);

            Map<String, String> data = dao.getResepById(idResep);
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

            JSpinner tanggalSpinner = new JSpinner(new SpinnerDateModel());
            tanggalSpinner.setEditor(new JSpinner.DateEditor(tanggalSpinner, "yyyy-MM-dd"));
            tanggalSpinner.setValue(Date.valueOf(data.get("tanggal")));

            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"belum ditebus", "ditebus"});
            statusCombo.setSelectedItem(data.get("status"));

            Object[] fields;
            if (role.equals("admin")) {
                fields = new Object[]{
                        "Pasien:", pasienCombo,
                        "Dokter:", dokterCombo,
                        "Obat:", obatCombo,
                        "Penyakit:", penyakitField,
                        "Tanggal:", tanggalSpinner,
                        "Status:", statusCombo
                };
            } else {
                fields = new Object[]{
                        "Pasien:", pasienCombo,
                        "Dokter:", dokterCombo,
                        "Obat:", obatCombo,
                        "Penyakit:", penyakitField,
                        "Tanggal:", tanggalSpinner
                };
            }

            int confirm = JOptionPane.showConfirmDialog(null, fields, "Edit Resep", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                String penyakit = penyakitField.getText();
                Date sqlDate = new Date(((java.util.Date) tanggalSpinner.getValue()).getTime());
                String idPasien = pasienMap.get(pasienCombo.getSelectedItem());
                String idDokter = dokterMap.get(dokterCombo.getSelectedItem());
                String idObat = obatMap.get(obatCombo.getSelectedItem());

                if (role.equals("admin")) {
                    String status = (String) statusCombo.getSelectedItem();
                    dao.updateResep(idResep, penyakit, sqlDate, idPasien, idDokter, idObat, status);
                } else {
                    dao.updateResepTanpaStatus(idResep, penyakit, sqlDate, idPasien, idDokter, idObat);
                }

                JOptionPane.showMessageDialog(null, "Resep berhasil diperbarui.");
                refreshTable();
            }
        } catch (Exception e) {
            handleError("Edit Error", e);
        }
    }

    private void hapusResep() {
    try {
        List<ResepPasien> list = dao.getAllResepLengkap();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada resep yang tersedia.");
            return;
        }

        JComboBox<String> combo = new JComboBox<>();
        Map<String, String> map = new HashMap<>();
        for (ResepPasien r : list) {
            String label = r.getNamaPasien() + " - " + r.getNamaDokter() + " (" + r.getIdResep() + ")";
            combo.addItem(label);
            map.put(label, r.getIdResep());
        }

        int result = JOptionPane.showConfirmDialog(null, combo, "Pilih Resep untuk Dihapus", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        String selected = combo.getSelectedItem().toString();
        String id = map.get(selected);

        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus resep ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.deleteResep(id);
            JOptionPane.showMessageDialog(null, "Resep berhasil dihapus.");
            refreshTable();
        }

    } catch (Exception e) {
        handleError("Delete Error", e);
    }
}


    private void refreshTable() {
        // Tambahkan kode refresh tabel jika kamu punya tampilan tabel
    }

    private void handleError(String title, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }
}
