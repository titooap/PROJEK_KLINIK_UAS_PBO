/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Pasien {
    private String idPasien;
    private String nama;
    private String alamat;
    private String telepon;

    public Pasien(String idPasien, String nama, String alamat, String telepon) {
        this.idPasien = idPasien;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    // Getter
    public String getIdPasien() {
        return idPasien;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    // Setter (jika kamu butuh ubah datanya)
    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}

