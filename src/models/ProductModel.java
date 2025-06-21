/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Windows 10
 */
public class ProductModel {
    public int id;
    public String name;
    public String sku;
    public int price;
    public int categoryId;
    public String categoryName;
    
    public void setProduct(
       int id,
       String name,
       String sku,
       int price,
       String categoryName,
       int categoryId
    ){
       this.id = id;
       this.name = name;
       this.sku = sku;
       this.price = price;
       this.categoryName = categoryName;
       this.categoryId = categoryId;
    }
    
    public void createProduct(
      String name,
      String sku,
      int price,
      int categoryId
    ){
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.categoryId = categoryId;
    }
}
