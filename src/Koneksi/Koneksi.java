/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asus
 */
public class Koneksi {
    public Connection connection;
    
    public String dbUrl = "jdbc:mysql://localhost:3306/pbo_uas?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public String dbUser = "root";
    public String dbPass = "alvin123";
    
    public Koneksi(){
        try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(dbUrl,dbUser,dbPass);
         System.out.println("Koneksi Ke Database Berhasil");
        } catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
}
