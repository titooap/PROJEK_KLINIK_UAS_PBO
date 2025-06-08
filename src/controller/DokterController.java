package controller;

import dao.DokterDAO;
import model.Dokter;
import java.util.List;

/**
 * Controller untuk mengelola data dokter, sebagai perantara antara view dan model.
 * Menangani logika bisnis terkait operasi CRUD dokter.
 */
public class DokterController {

    /**
     * Menambahkan dokter baru ke sistem.
     * @param id ID unik dokter
     * @param nama Nama lengkap dokter
     * @param spesialisasi Bidang spesialisasi dokter
     * @param idUser ID user terkait (untuk autentikasi)
     * @throws IllegalArgumentException jika ID atau nama kosong
     */
    public void tambahDokter(String id, String nama, String spesialisasi, String idUser) {
        if (id.isEmpty() || nama.isEmpty()) {
            throw new IllegalArgumentException("ID dan nama wajib diisi");
        }

        Dokter dokter = new Dokter(id, nama, spesialisasi, idUser);
        DokterDAO.insertDokter(dokter);
    }

    /**
     * Memperbarui data dokter yang sudah ada.
     * @param id ID dokter yang akan diupdate
     * @param nama Nama baru dokter
     * @param spesialisasi Spesialisasi baru
     * @param idUser ID user terkait
     */
    public void updateDokter(String id, String nama, String spesialisasi, String idUser) {
        Dokter dokter = new Dokter(id, nama, spesialisasi, idUser);
        DokterDAO.updateDokter(dokter);
    }

    /**
     * Menghapus dokter dari sistem berdasarkan ID.
     * @param id ID dokter yang akan dihapus
     */
    public void hapusDokter(String id) {
        DokterDAO.deleteDokter(id);
    }

    /**
     * Mengambil daftar semua dokter yang terdaftar.
     * @return List berisi semua objek Dokter
     */
    public List<Dokter> ambilSemuaDokter() {
        return DokterDAO.getAllDokter();
    }
}