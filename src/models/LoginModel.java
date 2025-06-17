/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Asus
 */
public class LoginModel {
    public String name;
    public String password;
    public String roleName = "";
    public boolean isLogged = false;
    
    public void setLogin(String name, String password){
        this.name = name;
        this.password = password;
    }
    
    public void setLoginCredential(String name,String password,String roleName){
        this.name = name;
        this.password = password;
        this.roleName = roleName;
    }
    
    public void setIsLogged(boolean bool){
        this.isLogged = bool;
    }
    
    public boolean isLogged(){
        return this.isLogged;
    }
    
    public String getRole(){
        return this.roleName;
    }
}
