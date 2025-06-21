/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Asus
 */
public class ProductCategoryModel {
    public String name;
    public int id;
    
    public ProductCategoryModel(String name, int id){
        this.name = name;
        this.id = id;
    }
    
    public void setCategoryFull(String name,int id){
      this.name = name;
      this.id = id;
    }
    
    public void setCategory(String name){
        this.name = name;
    }
}
