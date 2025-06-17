/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package register;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.RegisterModel;
import models.RoleModel;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class RegisterController {
    Koneksi koneksiInit;
    Connection conn;
    ResultSet resultSet;
    Statement statement;
    PreparedStatement preparedStatement;
    
    ArrayList<String> roleModels = new ArrayList<>();
    
    public RegisterController(){
        try {
          koneksiInit = new Koneksi();
          conn = koneksiInit.connection;
        } catch(Exception e){
          System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<String> getRoles(){
        try {
          String getRoleQuery = "SELECT * FROM role";
          ArrayList<String> roleItems = new ArrayList<>();
          
          statement = conn.createStatement();
          resultSet = statement.executeQuery(getRoleQuery);
                    
          while(resultSet.next()){
              String roleName = resultSet.getString("name");
              int id = resultSet.getInt("id");
              String idStr= String.valueOf(id);
              roleItems.add(idStr + " " + roleName);
          }
            
          System.out.println(roleItems.size());
          
          return roleItems;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return this.roleModels;
        }
    }
    
    public boolean handleRegister(RegisterModel registerDto){
        try {
          String insertQuery = "INSERT INTO user (name,password,role_id)" +
                               " VALUES('"+registerDto.name+"','"+registerDto.password+"','"+registerDto.roleId+"')";
          
          System.out.println(insertQuery);
          
          preparedStatement = conn.prepareStatement(insertQuery);
          
          preparedStatement.executeUpdate();
          
          preparedStatement.close();
          conn.close();
          
          return true;
          
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
}
