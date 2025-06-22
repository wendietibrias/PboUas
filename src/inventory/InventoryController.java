/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;

import models.InventoryModel;
import Koneksi.Koneksi;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author deadg
 */
public class InventoryController {
    public Koneksi koneksiInit;
    public Connection conn;
    
    public InventoryController() {
        try {
           koneksiInit = new Koneksi();
           conn = koneksiInit.connection;
        } catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<InventoryModel> getInventoryByWarehouse(int warehouseId) {
        ArrayList<InventoryModel> inventoryList = new ArrayList<>();
        
        String sql = "SELECT i.available_qty, w.name AS warehouse_name, p.name AS product_name, p.sku AS product_sku "
               + "FROM inventory i "
               + "JOIN products p ON i.product_id = p.id "
               + "JOIN warehouse w ON i.warehouse_id = w.id "
               + "WHERE i.warehouse_id = ?";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, warehouseId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int availableQty = rs.getInt("available_qty");
                String warehouseName = rs.getString("warehouse_name");
                String productName = rs.getString("product_name");
                String productSKU = rs.getString("product_sku");
                
                InventoryModel item = new InventoryModel(availableQty, productSKU, productName, warehouseName);
                inventoryList.add(item);    
            }
        } catch(SQLException e){
          System.out.println(e.getMessage());
        }
        
        return inventoryList;
    }
}
