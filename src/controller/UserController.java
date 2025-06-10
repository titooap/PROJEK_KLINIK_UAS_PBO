/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.User;
import dao.UserDAO;

public class UserController {
    public static List<User> ambilSemuaUser() {
        return UserDAO.getAllUsers();
    }
}

