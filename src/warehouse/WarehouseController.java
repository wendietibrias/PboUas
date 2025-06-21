/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse;
import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.WarehouseModel;
/**
 *
 * @author Windows 10
 */
public class WarehouseController {
    public Connection conn;
    public Koneksi koneksiInit;
    
    public WarehouseController(){
        try{
          koneksiInit = new Koneksi();
          conn = koneksiInit.connection;
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public WarehouseModel getOne(String id){
        WarehouseModel warehouseModel = new WarehouseModel("",0);
        
         try {
          String getQuery = "SELECT * FROM warehouse WHERE id='"+id+"'";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(getQuery);
          
          while(resultSet.next()){
              String name = resultSet.getString("name");
              int idWarehouse = resultSet.getInt("id");
              
              warehouseModel.setWarehouse(name, idWarehouse);
          }
          
          return warehouseModel;  
        } catch(Exception e){
          System.out.println(e.getMessage());
          return warehouseModel;
        }
    }
    
    public ArrayList<WarehouseModel> warehouseRenders(){
        ArrayList<WarehouseModel> warehouseItems = new ArrayList<>();
        
        try {
          String getQuery = "SELECT * FROM warehouse";
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(getQuery);
          
          while(resultSet.next()){
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            warehouseItems.add(new WarehouseModel(name, id));
          }
          
          return warehouseItems;  
        } catch(Exception e){
          System.out.println(e.getMessage());
          return warehouseItems;
        }
    }
    
    public boolean handleDelete(String id){
      try {
        String deleteQuery = "DELETE FROM warehouse WHERE id='"+id+"'";
        PreparedStatement prepareStatement = conn.prepareStatement(deleteQuery);
              
        prepareStatement.executeUpdate();
        
        return true;
      } catch(Exception e){
          System.out.println(e.getMessage());
          return false;
      }
    }
    
        public boolean handleSubmit(WarehouseModel warehouseDto,String id){
        try {
          if(id.length() > 0){
              String updateQuery = "UPDATE warehouse SET name='"+warehouseDto.name+"' WHERE id='"+id+"'";
              PreparedStatement prepareStatement = conn.prepareStatement(updateQuery);
              
              prepareStatement.executeUpdate();
          } else {
              String insertQuery = "INSERT INTO warehouse (name) VALUES('"+warehouseDto.name+"')";
              
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
