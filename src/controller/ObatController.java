/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ObatDAO;
import model.Obat;
import java.util.List;

public class ObatController {

    public void tambahObat(String id, String nama, int stok, double harga) {
        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan nama obat wajib diisi.");
            return;
        }

        Obat o = new Obat(id, nama, stok, harga);
        ObatDAO.insertObat(o);
    }

    public void updateObat(String id, String nama, int stok, double harga) {
        Obat o = new Obat(id, nama, stok, harga);
        ObatDAO.updateObat(o);
    }

    public void hapusObat(String id) {
        ObatDAO.deleteObat(id);
    }

    public List<Obat> ambilSemuaObat() {
        return ObatDAO.getAllObat();
    }
}

