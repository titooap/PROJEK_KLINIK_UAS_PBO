/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author avvjelly
 */
public class Obat {
    private String idObat;
    private String namaObat;
    private int stok;
    private double harga;

    public Obat(String idObat, String namaObat, int stok, double harga) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.stok = stok;
        this.harga = harga;
    }

    // Getters
    public String getIdObat() {
        return idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public int getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }

    // Setters
    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}

