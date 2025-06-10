/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Admin extends User {
    private String idAdmin;

    public Admin(String idAdmin, String nama, String idUser) {
        super(idUser, nama);
        this.idAdmin = idAdmin;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    @Override
    public String getRole() {
        return "admin";
    }
}
