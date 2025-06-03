/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DokterDAO;
import model.Dokter;
import java.util.List;

public class DokterController {

    public void tambahDokter(String id, String nama, String spesialisasi, String idUser) {
        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan nama wajib diisi.");
            return;
        }

        Dokter d = new Dokter(id, nama, spesialisasi, idUser);
        DokterDAO.insertDokter(d);
    }

    public void updateDokter(String id, String nama, String spesialisasi, String idUser) {
        Dokter d = new Dokter(id, nama, spesialisasi, idUser);
        DokterDAO.updateDokter(d);
    }

    public void hapusDokter(String id) {
        DokterDAO.deleteDokter(id);
    }

    public List<Dokter> ambilSemuaDokter() {
        return DokterDAO.getAllDokter();
    }
}

