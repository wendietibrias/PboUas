/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Asus
 */
public class RegisterModel {
    public String name;
    public String password;
    public String roleName;
    public String roleId;
    
    public void setRegister(String name,String password,String roleId){
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }
}
