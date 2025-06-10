/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class User {
    private String idUser;
    private String nama;
    private String username;
    private String password;
    private String role;

    // Constructor minimal → dipakai oleh Dokter
    public User(String idUser, String nama) {
        this.idUser = idUser;
        this.nama = nama;
    }

    // Constructor lengkap → dipakai oleh UserDAO
    public User(String idUser, String nama, String username, String password, String role) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter-setter lengkap
    public String getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role != null ? role : "user";
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}




