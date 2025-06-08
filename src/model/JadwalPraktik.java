/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author avvjelly
 */
public class JadwalPraktik {
    private String idJadwal;
    private String hari;
    private String jamMulai;
    private String jamBerakhir;

    public JadwalPraktik(String idJadwal, String hari, String jamMulai, String jamBerakhir) {
        this.idJadwal = idJadwal;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamBerakhir = jamBerakhir;
    }

    public String getIdJadwal() { return idJadwal; }
    public String getHari() { return hari; }
    public String getJamMulai() { return jamMulai; }
    public String getJamBerakhir() { return jamBerakhir; }
}

