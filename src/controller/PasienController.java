package controller;

import dao.PasienDAO;
import model.Pasien;
import java.util.List;

/**
 * Controller untuk manajemen data pasien.
 * Menangani logika bisnis terkait operasi CRUD pasien.
 */
public class PasienController {

    /**
     * Menambahkan pasien baru ke sistem.
     * @param id ID pasien (unik)
     * @param nama Nama lengkap pasien
     * @param alamat Alamat pasien
     * @param telepon Nomor telepon pasien
     * @throws IllegalArgumentException jika ID atau nama kosong
     */
    public void tambahPasien(String id, String nama, String alamat, String telepon) {
        if (id.isEmpty() || nama.isEmpty()) {
            throw new IllegalArgumentException("ID dan nama wajib diisi");
        }

        Pasien pasien = new Pasien(id, nama, alamat, telepon);
        PasienDAO.insertPasien(pasien);
    }

    /**
     * Memperbarui data pasien yang sudah ada.
     * @param id ID pasien yang akan diupdate
     * @param nama Nama baru pasien
     * @param alamat Alamat baru
     * @param telepon Nomor telepon baru
     */
    public void updatePasien(String id, String nama, String alamat, String telepon) {
        Pasien pasien = new Pasien(id, nama, alamat, telepon);
        PasienDAO.updatePasien(pasien);
    }

    /**
     * Menghapus pasien berdasarkan ID.
     * @param id ID pasien yang akan dihapus
     */
    public void hapusPasien(String id) {
        PasienDAO.deletePasien(id);
    }

    /**
     * Mengambil daftar semua pasien yang terdaftar.
     * @return List berisi semua objek Pasien
     */
    public List<Pasien> ambilSemuaPasien() {
        return PasienDAO.getAllPasien();
    }
}