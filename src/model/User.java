/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class User {
    protected String idUser;
    protected String nama;

    public User(String idUser, String nama) {
        this.idUser = idUser;
        this.nama = nama;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public abstract String getRole();
}


