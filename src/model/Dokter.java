/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Dokter extends User {
    private String idDokter;
    private String spesialisasi;

    public Dokter(String idDokter, String nama, String spesialisasi, String idUser) {
        super(idUser, nama);
        this.idDokter = idDokter;
        this.spesialisasi = spesialisasi;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    @Override
    public String getRole() {
        return "dokter";
    }
}


