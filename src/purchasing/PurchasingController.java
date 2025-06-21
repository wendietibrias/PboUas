/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasing;

import java.util.ArrayList;
import models.ProductModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import models.PurchasingModel;
/**
 *
 * @author Windows 10
 */
public class PurchasingController {
    Koneksi.Koneksi koneksiInit;
    public Connection conn;
    
    public PurchasingController(){
        koneksiInit = new Koneksi.Koneksi();
        conn = koneksiInit.connection;
    }
    
    public ArrayList<ProductModel> renderProducts(){
        ArrayList<ProductModel> productItems = new ArrayList<>();
        
        try {
          String productQuery = "SELECT products.name,products.price,products.sku,products.id,product_category.name as category_name " +
                                " LEFT JOIN product_category";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(productQuery);
          
          while(resultSet.next()){
               int id = resultSet.getInt("id");
               int categoryId = resultSet.getInt("product_category_id");
               String productName = resultSet.getString("name");
               int price = resultSet.getInt("price");
               String categoryName = resultSet.getString("category_name");
               String sku = resultSet.getString("sku");
               
               ProductModel productModel = new ProductModel();
               productModel.setProduct(id, productName, sku, price, categoryName, categoryId);
               
               productItems.add(productModel);
          }
          
          return productItems;
           
        } catch(Exception e){
           System.out.println(e.getMessage());
           return productItems;
        }
    }
   
    public boolean handleSubmit(PurchasingModel purchasingDto){
        try {
//          String insertQuery = "INSERT INTO purchase_request VALUES()"
          return true;   
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
    
    public boolean handleDeleteDetail(String id){
       return false;
    }
    
    public boolean handleSubmitDetail(){
        return false;
    }
    
    public boolean handleDelete(String id){
        try {
          
          return true;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
}
