/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RuanganDAO;
import model.Ruangan;
import java.util.List;

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

    public void hapusRuangan(String id) {
        RuanganDAO.deleteRuangan(id);
    }

    public List<Ruangan> ambilSemuaRuangan() {
        return RuanganDAO.getAllRuangan();
    }
}

