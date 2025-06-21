/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.ProductCategoryModel;

/**
 *
 * @author Asus
 */
public class ProductCategoryController {
    Koneksi.Koneksi koneksiInit;
    Connection conn;
    
    public ProductCategoryController(){
        try {
          koneksiInit = new Koneksi.Koneksi();
          conn = koneksiInit.connection;
          
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public ProductCategoryModel getOne(String id){
        ProductCategoryModel kategoriModel = new ProductCategoryModel("", 0);
        try {
           String getQuery = "SELECT * FROM product_category WHERE id='"+id+"'";
           Statement statement = conn.createStatement();
           
           ResultSet resultSet = statement.executeQuery(getQuery);
           
           while(resultSet.next()){
               kategoriModel.setCategoryFull(resultSet.getString("name"),resultSet.getInt("id"));
           }
           
           return kategoriModel;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return kategoriModel;
        }
    }
    
    public ArrayList<ProductCategoryModel> renderCategoriesLabel(){
        ArrayList<ProductCategoryModel> categoryItems = new ArrayList<>();
        
        try {
          String getProductCategories = "SELECT * FROM product_category";
          
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(getProductCategories);
          
          while(resultSet.next()){
              String categoryName = resultSet.getString("name");
              int id = resultSet.getInt("id");
              
              ProductCategoryModel kategoriItem = new ProductCategoryModel(categoryName,id);
             
              categoryItems.add(kategoriItem);
          }
           
          return categoryItems;
        } catch(Exception e){
            return categoryItems;
        }
    }
    
    
    public boolean handleDelete(String id){
        try {
           String deleteQuery = "DELETE FROM product_category WHERE id = '"+id+"'";
           PreparedStatement prepareStatement = conn.prepareStatement(deleteQuery);
           
           prepareStatement.executeUpdate();
           
           return true;
        } catch(Exception e){
            return false;
        }
    }
   
    public boolean handleSubmit(ProductCategoryModel dto,String id){
        try {
           if(id.length() > 0){
               
           String insertQuery = "UPDATE product_category set name = '"+dto.name+"' WHERE id='"+id+"'";
           PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
           
           preparedStatement.executeUpdate();
           } else {
               
           String insertQuery = "INSERT INTO product_category (name) VALUES('"+dto.name+"')";
           PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
           
           preparedStatement.executeUpdate();
           }
          
           return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
