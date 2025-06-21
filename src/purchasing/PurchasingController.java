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
import models.PurchasingDetailModel;
import models.PurchasingModel;
import models.WarehouseModel;
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
    
       public ArrayList<WarehouseModel> renderWarehouses(){
        ArrayList<WarehouseModel> warehouseItems = new ArrayList<>();
        
        try {
          String productQuery = "SELECT * FROM warehouse";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(productQuery);
          
          while(resultSet.next()){
               int id = resultSet.getInt("id");
               String warehouseName = resultSet.getString("name");
               
               WarehouseModel warehouseModel = new WarehouseModel(warehouseName,id);
               
               warehouseItems.add(warehouseModel);
          }
          
          return warehouseItems;
           
        } catch(Exception e){
           System.out.println(e.getMessage());
           return warehouseItems;
        }
    }
    
    public ArrayList<ProductModel> renderProducts(){
        ArrayList<ProductModel> productItems = new ArrayList<>();
        
        try {
          String productQuery = "SELECT products.product_category_id,products.name,products.price,products.sku,products.id,product_category.name as category_name " +
                                " FROM products" + 
                                " LEFT JOIN product_category ON products.product_category_id = product_category.id";
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
    
    public PurchasingModel getPurchasing(String purchaseNumber){
       PurchasingModel purchaseModel = new PurchasingModel();

      try {
        String getQuery = "SELECT * FROM purchase_request" ;
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(getQuery);
        
        
        while(resultSet.next()){
            String purchaseCode = resultSet.getString("purchase_number");
            int id = resultSet.getInt("id");
        }
          
        return purchaseModel;
        
      } catch(Exception e){
        System.out.println(e.getMessage());
        return purchaseModel;
      }
    }
   
    public int handleSubmit(PurchasingModel purchasingDto){
        try {
          String insertQuery = "INSERT INTO purchase_request (purchase_number,warehouse_id) VALUES('"+purchasingDto.purchaseNumber+"','"+purchasingDto.warehouseId+"')";
          PreparedStatement prepareStatement= conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
          
          prepareStatement.executeUpdate();
          
          int purchaseId = 0;
         
          try(ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
            if(generatedKeys.next()){
               purchaseId = generatedKeys.getInt(1);
            }
          } catch(Exception e){
            purchaseId = 0;
            System.out.println(e.getMessage());
          }
          
          return purchaseId;   
        } catch(Exception e){
          System.out.println(e.getMessage());
          return 0;
        }
    }
    
    public boolean handleDeleteDetail(String id){
       return false;
    }
    
    public boolean handleSubmitDetail(PurchasingDetailModel purchasingDto){
        try {
          String insertQuery = "INSERT INTO purchase_request_details (product_id,qty,price,subtotal,grandtotal)";
          return true;
        } catch(Exception e){
          return false;
        }
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
