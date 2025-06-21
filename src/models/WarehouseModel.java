/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Windows 10
 */
public class WarehouseModel {
    public String name;
    public int id;
    
    public WarehouseModel(String name, int id){
        this.name = name;
        this.id = id;
    }
    
    public void createWarehouse(String name){
        this.name = name;
    }
    
    public void setWarehouse(String name, int id){
        this.name = name;
        this.id = id;
    }
}
