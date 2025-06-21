/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Asus
 */
public class UserModel {
   public static String name;
   public static int userId;
   public static String roleName;

   public static void setUser(String nameUser,String role, int idUser)   {
       name = nameUser;
       roleName = role;
       userId = idUser;
   }
   
   public static String getName(){
       return name;
   }
}
