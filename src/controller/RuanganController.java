/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RuanganDAO;
import java.sql.SQLException;
import java.util.List;
import model.Ruangan;
public class RuanganController {

    public void tambahRuangan(String id, String nama, int lantai, int kapasitas) {
        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan nama ruangan wajib diisi.");
            return;
        }

        Ruangan r = new Ruangan(id, nama, lantai, kapasitas);
        RuanganDAO.insertRuangan(r);
    }

    public void updateRuangan(String id, String nama, int lantai, int kapasitas) {
        Ruangan r = new Ruangan(id, nama, lantai, kapasitas);
        RuanganDAO.updateRuangan(r);
    }

     /**
     * Menghapus ruangan berdasarkan ID.
     * @param id ID ruangan yang akan dihapus
     * @throws IllegalArgumentException jika ID kosong
     */
    public void hapusRuangan(String id) throws SQLException {
        RuanganDAO.deleteRuangan(id);
    }
    
    /**
     * Mengambil daftar semua ruangan yang terdaftar.
     * @return List berisi semua objek Ruangan
     */
    public List<Ruangan> ambilSemuaRuangan() {
        return RuanganDAO.getAllRuangan();
    }
}

