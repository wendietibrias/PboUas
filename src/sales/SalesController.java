/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import models.InventoryModel;
import models.ProductModel;
import models.PurchasingModel;
import models.SalesModel;
import models.WarehouseModel;
import models.salesDetailsModel;

/**
 *
 * @author Asus
 */
public class SalesController {
    Koneksi koneksiInit;
    Connection conn;
    
     public ArrayList<SalesModel> renderSales(){
       ArrayList<SalesModel> salesItems = new ArrayList<>();
        
        try {
          String purchasingQuery = "SELECT sales.id, sales.sales_date,sales.id,sales.sales_number,sales.sub_total,sales.grand_total,sales.warehouse_id,warehouse.name " + 
                                   " FROM sales" +
                                   " LEFT JOIN warehouse ON sales.warehouse_id = warehouse.id";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(purchasingQuery);
          
          while(resultSet.next()){
              SalesModel salesModelItem = new SalesModel();
              int id = resultSet.getInt("id");
              String salesNumber = resultSet.getString("sales_number");
              int subtotal = resultSet.getInt("sub_total");
              int grandtotal = resultSet.getInt("grand_total");
              int warehouseId =resultSet.getInt("warehouse_id");
              String warehouseName = resultSet.getString("name");
              Date purchaseDate = resultSet.getDate("sales_date");
              
              salesModelItem.setSales(salesNumber,subtotal,grandtotal,warehouseId,warehouseName,purchaseDate,id);
              salesItems.add(salesModelItem);
          }
            
          
          statement.close();
          resultSet.close();
          
          return salesItems; 
        } catch(Exception e){
           System.out.println(e.getMessage());
           return salesItems;
        }
    }
    
    public ArrayList<salesDetailsModel> salesDetailItems(int salesId){
        ArrayList<salesDetailsModel> salesDetailItems = new ArrayList<>();
        
        try {                       
           String getQuery = "SELECT sales_details.qty,sales_details.id,sales_details.product_id,sales_details.sales_id,sales_details.sub_total,sales_details.grand_total,products.name,products.price,products.sku" +
                             " FROM sales_details" +
                             " LEFT JOIN products ON sales_details.product_id = products.id" + 
                             " WHERE sales_id='"+salesId+"'";
           
           Statement statement = conn.createStatement();
           ResultSet resultSet = statement.executeQuery(getQuery);
           
           while(resultSet.next()){
               String productName = resultSet.getString("name");
               String productSku = resultSet.getString("sku");
               int price = resultSet.getInt("price");
               int subtotal = resultSet.getInt("sub_total");
               int grandtotal = resultSet.getInt("grand_total");
               int productId = resultSet.getInt("product_id");
               int qtyInput = resultSet.getInt("qty");
               int id = resultSet.getInt("id");
               
               salesDetailsModel salesDetailModelItem = new salesDetailsModel();
               salesDetailModelItem.setSalesDetail(salesId,productId,qtyInput,subtotal,grandtotal,id,price,productName,productSku);
               
               salesDetailItems.add(salesDetailModelItem);
           }
           
           return salesDetailItems; 
        } catch(Exception e){
           System.out.println(e.getMessage());
           return salesDetailItems;
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
    
    public SalesController(){
        koneksiInit = new Koneksi();
        conn = koneksiInit.connection;
    }
    
    public int createSales(SalesModel salesDto){
        try {
          String insertQuery = "INSERT INTO sales (sales_number,warehouse_id,sales_date) VALUES('"+salesDto.salesNumber+"','"+salesDto.warehouseId+"','"+salesDto.salesDate+"')";
          PreparedStatement prepareStatement = conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
          
          int salesId = 0;
          
          prepareStatement.executeUpdate();
          
          try(ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
            if(generatedKeys.next()){
               salesId = generatedKeys.getInt(1);
            }
          } catch(Exception e){
            salesId = 0;
            System.out.println(e.getMessage());
          }
          
          return salesId;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return 0;
        }
    }
    
    public boolean createSalesDetails(salesDetailsModel salesDetailDto){
        try {
          String insertQuery = "INSERT INTO sales_details (product_id,sales_id,qty,sub_total,grand_total) VALUES('"+salesDetailDto.productId+"','"+salesDetailDto.salesId+"','"+salesDetailDto.qty+"','"+salesDetailDto.subtotal+"','"+salesDetailDto.grandtotal+"')";
          
          PreparedStatement prepareStatement = conn.prepareStatement(insertQuery);
          
          prepareStatement.executeUpdate();
          
          prepareStatement.close();
          
          return true;
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
    
    public boolean handleDeleteAll(int salesId){
        try {
          String deletePRQuery = "DELETE FROM sales WHERE id='"+salesId+"'";
          String deleteDetailQuery = "DELETE FROM sales_details WHERE sales_id = '"+salesId+"'";
          
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
            
            currentAvailableQty -= inventoryDto.availableQty;
          
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
    
     public boolean handleUpdateSales(int grandtotal,int subtotal, int currentSalesId){
        try {
           String updateQuery = "UPDATE sales SET sub_total='"+subtotal+"',grand_total='"+grandtotal+"' WHERE id='"+currentSalesId+"'";
           PreparedStatement prepareStatement = conn.prepareStatement(updateQuery);
           
           prepareStatement.executeUpdate();
           
           prepareStatement.close();
           
           return true;  
        } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
        }
    }
}
