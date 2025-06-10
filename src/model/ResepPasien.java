package model;

import java.sql.Date;

public class ResepPasien {
    private String idResep;
    private String penyakit;
    private Date tanggal;
    private String namaPasien;
    private String namaDokter;
    private String namaObat;
    private String status; // tambahkan status

    public ResepPasien(String idResep, String penyakit, Date tanggal, String namaPasien, String namaDokter, String namaObat, String status) {
        this.idResep = idResep;
        this.penyakit = penyakit;
        this.tanggal = tanggal;
        this.namaPasien = namaPasien;
        this.namaDokter = namaDokter;
        this.namaObat = namaObat;
        this.status = status; // set status
    }

    public String getIdResep() { return idResep; }
    public String getPenyakit() { return penyakit; }
    public Date getTanggal() { return tanggal; }
    public String getNamaPasien() { return namaPasien; }
    public String getNamaDokter() { return namaDokter; }
    public String getNamaObat() { return namaObat; }
    public String getStatus() { return status; } // getter status

    public void setIdResep(String idResep) { this.idResep = idResep; }
    public void setPenyakit(String penyakit) { this.penyakit = penyakit; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public void setNamaPasien(String namaPasien) { this.namaPasien = namaPasien; }
    public void setNamaDokter(String namaDokter) { this.namaDokter = namaDokter; }
    public void setNamaObat(String namaObat) { this.namaObat = namaObat; }
    public void setStatus(String status) { this.status = status; } // setter status
}
