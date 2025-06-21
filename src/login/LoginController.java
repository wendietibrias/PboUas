/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import models.LoginModel;
import models.RoleModel;
import models.UserModel;

/**
 *
 * @author Asus
 */
public class LoginController {
    public Koneksi connection;
    public Connection conn;
    public Statement statement;
    public ResultSet resultSet;
    
    public boolean handleLogin(LoginModel loginDto){
        try {
        connection = new Koneksi();
        conn = connection.connection;
        
        statement = conn.createStatement();
        
        String checkLoginCredentialsQuery = "SELECT user.name,user.password,role.name as role_name" 
                                                + " FROM user" 
                                                + " LEFT JOIN role ON user.role_id = role.id"
                                                + " WHERE user.name='"+loginDto.name+"' AND user.password='"+loginDto.password+"'";
        
        resultSet = statement.executeQuery(checkLoginCredentialsQuery);
        
        while(resultSet.next()){
            String name = resultSet.getString("name");
            String pass = resultSet.getString("password");
            String role = resultSet.getString("role_name");
            int userId = resultSet.getInt("id");
            
            System.out.println(name);
            System.out.println(pass);
            System.out.println(role);
            
            loginDto.setLoginCredential(name, pass, role);
            loginDto.setIsLogged(true);
            
            new UserModel().setUser(name,role,userId);
        }
         
         resultSet.close();
         statement.close();
         conn.close();
         
        
         return loginDto.isLogged();
         
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;   
        }
      
    }
}
