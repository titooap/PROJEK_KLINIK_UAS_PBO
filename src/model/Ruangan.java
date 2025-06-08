/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author avvjelly
 */
public class Ruangan {
    private String idRuangan;
    private String namaRuangan;
    private int lantai;
    private int kapasitas;

    public Ruangan(String idRuangan, String namaRuangan, int lantai, int kapasitas) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.lantai = lantai;
        this.kapasitas = kapasitas;
    }

    // Getters
    public String getIdRuangan() {
        return idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public int getLantai() {
        return lantai;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    // Setters
    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}

