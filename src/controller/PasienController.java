/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PasienDAO;
import model.Pasien;
import java.util.List;

public class PasienController {

    public void tambahPasien(String id, String nama, String alamat, String telepon) {
        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan nama wajib diisi.");
            return;
        }

        Pasien p = new Pasien(id, nama, alamat, telepon);
        PasienDAO.insertPasien(p);
    }

    public void updatePasien(String id, String nama, String alamat, String telepon) {
        Pasien p = new Pasien(id, nama, alamat, telepon);
        PasienDAO.updatePasien(p);
    }

    public void hapusPasien(String id) {
        PasienDAO.deletePasien(id);
    }

    public List<Pasien> ambilSemuaPasien() {
        return PasienDAO.getAllPasien();
    }
}
