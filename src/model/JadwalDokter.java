/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class JadwalDokter {
    private String idJadwalDokter;
    private String namaDokter;
    private String hari;
    private String namaRuangan;

    public JadwalDokter(String idJadwalDokter, String namaDokter, String hari, String namaRuangan) {
        this.idJadwalDokter = idJadwalDokter;
        this.namaDokter = namaDokter;
        this.hari = hari;
        this.namaRuangan = namaRuangan;
    }
    
    public String getIdJadwalDokter() {
        return idJadwalDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public String getHari() {
        return hari;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

}
