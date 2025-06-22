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
import java.util.Date;
import models.InventoryModel;
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
    
    public ArrayList<PurchasingModel> renderPurchasings(){
       ArrayList<PurchasingModel> purchasingItems = new ArrayList<>();
        
        try {
          String purchasingQuery = "SELECT purchase_request.purchase_date,purchase_request.id,purchase_request.purchase_number,purchase_request.subtotal,purchase_request.grandtotal,purchase_request.warehouse_id,warehouse.name " + 
                                   " FROM purchase_request" +
                                   " LEFT JOIN warehouse ON purchase_request.warehouse_id = warehouse.id";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(purchasingQuery);
          
          while(resultSet.next()){
              PurchasingModel purchaseModelItem = new PurchasingModel();
              int id = resultSet.getInt("id");
              String purchaseNumber = resultSet.getString("purchase_number");
              int subtotal = resultSet.getInt("subtotal");
              int grandtotal = resultSet.getInt("grandtotal");
              int warehouseId =resultSet.getInt("warehouse_id");
              String warehouseName = resultSet.getString("name");
              Date purchaseDate = resultSet.getDate("purchase_date");
              
              purchaseModelItem.setPurchasing(id, purchaseNumber, subtotal, subtotal, grandtotal, warehouseId, warehouseName,purchaseDate);
              purchasingItems.add(purchaseModelItem);
          }
            
          
          statement.close();
          resultSet.close();
          
          return purchasingItems; 
        } catch(Exception e){
           System.out.println(e.getMessage());
           return purchasingItems;
        }
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
          
          
          statement.close();
          resultSet.close();
          
          return warehouseItems;
           
        } catch(Exception e){
           System.out.println(e.getMessage());
           return warehouseItems;
        }
    }
       
    public ArrayList<PurchasingDetailModel> renderPurchaseDetails(int purchaseId){
        ArrayList<PurchasingDetailModel> purchasingItems = new ArrayList<>();
        
        try {
          String getQuery = "SELECT purchase_request_details.id,purchase_request_details.qty,purchase_request_details.purchase_request_id,purchase_request_details.subtotal,purchase_request_details.product_id,products.name,products.price,products.sku" +
                            " FROM purchase_request_details " +
                            " LEFT JOIN products ON purchase_request_details.product_id = products.id" + 
                            " WHERE purchase_request_id = '"+purchaseId+"'";
          
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(getQuery);
          
          while(resultSet.next()){
              PurchasingDetailModel purchasingDetailItem = new PurchasingDetailModel();
              
              String productName = resultSet.getString("name");
              int productPrice = resultSet.getInt("price");
              String productSku = resultSet.getString("sku");
              int subtotal = resultSet.getInt("subtotal");
              int productId = resultSet.getInt("product_id");
              int qty = resultSet.getInt("qty");
              int id = resultSet.getInt("id");
              
              purchasingDetailItem.setPurchasingDetail(id,productName, productPrice, productSku, subtotal, productId, qty);
              purchasingItems.add(purchasingDetailItem);
          }
            
          statement.close();
          resultSet.close();
          return purchasingItems;  
        } catch(Exception e){
           System.out.println(e.getMessage());
           return purchasingItems;
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
          
          
          statement.close();
          resultSet.close();
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
          
        
          statement.close();
          resultSet.close();
        return purchaseModel;
        
      } catch(Exception e){
        System.out.println(e.getMessage());
        return purchaseModel;
      }
    }
    
    public boolean handleCreateInventory(InventoryModel inventoryDto){
        try {
          String checkExistQuery = "SELECT * FROM inventory WHERE product_id='"+inventoryDto.productId+"' AND warehouse_id='"+inventoryDto.warehouseId+"'";
          Statement statement = conn.createStatement();
          
          int existingInventoryId = 0;
          int currentAvailableQty = 0;
          
          ResultSet resultSet= statement.executeQuery(checkExistQuery);
          
          if(!resultSet.isBeforeFirst()){  
            String insertQuery = "INSERT INTO inventory (warehouse_id,product_id,available_qty) VALUES('"+inventoryDto.warehouseId+"','"+inventoryDto.productId+"','"+inventoryDto.availableQty+"')";
            PreparedStatement prepareStatement = conn.prepareStatement(insertQuery);
          
            prepareStatement.executeUpdate();              
          } else {
            while(resultSet.next()) {
               currentAvailableQty = resultSet.getInt("available_qty");
               existingInventoryId = resultSet.getInt("id");
            }
            
            currentAvailableQty += inventoryDto.availableQty;
          
            String updateQuery = "UPDATE inventory SET available_qty='"+currentAvailableQty+"' WHERE id='"+existingInventoryId+"'";
            PreparedStatement prepareStatement = conn.prepareStatement(updateQuery);
            
            prepareStatement.executeUpdate();
          }
         
          statement.close();
          resultSet.close();
          conn.close();
          
          return true;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
   
    public boolean handleUpdatePr(int grandtotal,int subtotal, int currentPrId){
        try {
           String updateQuery = "UPDATE purchase_request SET subtotal='"+subtotal+"',grandtotal='"+grandtotal+"' WHERE id='"+currentPrId+"'";
           PreparedStatement prepareStatement = conn.prepareStatement(updateQuery);
           
           prepareStatement.executeUpdate();
           
           return true;  
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
    
    public int handleSubmit(PurchasingModel purchasingDto){
        try {
          String insertQuery = "INSERT INTO purchase_request (purchase_number,purchase_date,warehouse_id) VALUES('"+purchasingDto.purchaseNumber+"','"+purchasingDto.purchaseDate+"','"+purchasingDto.warehouseId+"')";
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
          String insertQuery = "INSERT INTO purchase_request_details (purchase_request_id,product_id,qty,price,subtotal,grandtotal) VALUES('"+purchasingDto.purchasingId+"','"+purchasingDto.productId+"','"+purchasingDto.qty+"','"+purchasingDto.price+"','"+purchasingDto.subtotal+"','"+purchasingDto.subtotal+"')";
          PreparedStatement prepareStatement = conn.prepareStatement(insertQuery);
          
          prepareStatement.executeUpdate();
          
          return true;
        } catch(Exception e){
          System.out.println(e.getMessage());
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
    
    public boolean handleDeleteAll(int currentPrId){
        try {
          String deletePRQuery = "DELETE FROM purchase_request WHERE id='"+currentPrId+"'";
          String deleteDetailQuery = "DELETE FROM purchase_request_details WHERE purchase_request_id = '"+currentPrId+"'";
          
          PreparedStatement prepareStatement = conn.prepareStatement(deletePRQuery);
          PreparedStatement prepareStatement2 = conn.prepareStatement(deleteDetailQuery);
          
          prepareStatement.executeUpdate();
          prepareStatement2.executeUpdate();
          
          return true;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
}
