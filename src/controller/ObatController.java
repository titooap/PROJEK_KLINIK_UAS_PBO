package controller;

import dao.ObatDAO;
import model.Obat;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller untuk manajemen data obat.
 * Menangani logika bisnis terkait operasi CRUD obat.
 */
public class ObatController {

    /**
     * Menambahkan obat baru.
     * @param id ID obat
     * @param nama Nama obat
     * @param stok Jumlah stok
     * @param harga Harga per unit
     * @throws IllegalArgumentException jika ID atau nama kosong
     */
    public void tambahObat(String id, String nama, int stok, double harga) throws SQLException {

        Obat obat = new Obat(id, nama, stok, harga);
        ObatDAO.insertObat(obat);
    }

    /**
     * Memperbarui data obat yang sudah ada.
     * @param id ID obat yang akan diupdate
     * @param nama Nama baru obat
     * @param stok Stok baru
     * @param harga Harga baru
     */
    public void updateObat(String id, String nama, int stok, double harga) {
        Obat obat = new Obat(id, nama, stok, harga);
        ObatDAO.updateObat(obat);
    }

    /**
     * Menghapus obat berdasarkan ID.
     * @param id ID obat yang akan dihapus
     * @throws SQLException 
     */
    public void hapusObat(String id) throws SQLException {
        ObatDAO.deleteObat(id);
    }

    /**
     * Mengambil daftar semua obat.
     * @return List berisi semua objek Obat
     */
    public List<Obat> ambilSemuaObat() {
        return ObatDAO.getAllObat();
    }
}