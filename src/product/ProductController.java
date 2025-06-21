/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product;

import Koneksi.Koneksi;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import models.ProductModel;
/**
 *
 * @author Asus
 */
public class ProductController {
    public Koneksi koneksiInit;
    public Connection conn;
    
    public ProductController(){
        try {
           koneksiInit = new Koneksi();
           conn = koneksiInit.connection;
        } catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    public ProductModel getOne(String id){
        ProductModel productModel= new ProductModel();
        
        try {
          String query = "SELECT * FROM products WHERE id='"+id+"'";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(query);
          
          while(resultSet.next()){
              int ids = resultSet.getInt("id");
              String name = resultSet.getString("name");
              int price = resultSet.getInt("price");
              String convertedPrice = String.valueOf(price);
              int productCategoryId = resultSet.getInt("product_category_id");
              String sku = resultSet.getString("sku");
              
              productModel.setProduct(ids, name, sku, price, "",productCategoryId);
          }
            
          return productModel;
        } catch(Exception e){
           System.out.println(e.getMessage());
           return productModel;
        }
    }
    
    public ArrayList<String> getCategories(){
        try {
          ArrayList<String> categoryItems = new ArrayList<>();
          String getCategory = "SELECT * FROM product_category";
          Statement statement = conn.createStatement();
          
          ResultSet resultSet = statement.executeQuery(getCategory);
          
          while(resultSet.next()){
             String name = resultSet.getString("name");
             int id = resultSet.getInt("id");
             String idCategory = String.valueOf(id);
             
             categoryItems.add(idCategory + " " + name);
          }
          
          return categoryItems;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ProductModel> renderProducts(){
        ArrayList<ProductModel> productItems = new ArrayList<>();
        try {
          String getAllProduct = "SELECT products.id, products.name,products.sku,products.price,product_category.name as category_name" + 
                                 " FROM products" +
                                 " LEFT JOIN product_category ON products.product_category_id = product_category.id";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(getAllProduct);
            
          while(resultSet.next()){
              ProductModel productModel = new ProductModel();
              String name = resultSet.getString("name");
              int price = resultSet.getInt("price");
              String categoryName = resultSet.getString("category_name");
              String sku = resultSet.getString("sku");
              int id = resultSet.getInt("id");
              productModel.setProduct(id,name, sku, price, categoryName,0);
              
              productItems.add(productModel);
          }
          
          return productItems;  
        } catch(Exception e){
          System.out.println(e.getMessage());
          return productItems;
        }
    }
    
    public boolean handleDelete(String id){
        try {
          String deleteQuery = "DELETE FROM products WHERE id='"+id+"'";
          PreparedStatement prepareStatement = conn.prepareStatement(deleteQuery);
          
          prepareStatement.executeUpdate();
             
          return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean handleSubmitProduct(ProductModel productDto,String id){
        try {
         if(id.length()>0){
             String updateQuery = "UPDATE products SET name='"+productDto.name+"',price='"+productDto.price+"',sku='"+productDto.sku+"',product_category_id='"+productDto.categoryId+"' WHERE id = '"+id+"'";
             PreparedStatement prepareStatement = conn.prepareStatement(updateQuery);
             
             prepareStatement.executeUpdate();
         } else {
             String insertQuery = "INSERT INTO products (name,price,sku,product_category_id) VALUES('"+productDto.name+"','"+productDto.price+"','"+productDto.sku+"','"+productDto.categoryId+"')";
             
             PreparedStatement prepareStatement = conn.prepareStatement(insertQuery);
             prepareStatement.executeUpdate();
         }
           
         return true;        
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
