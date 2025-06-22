/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class PurchasingModel {
    public int id;
    public String purchaseNumber;
    public int subtotal;
    public int grandtotal;
    public int warehouseId;
    public int qtyTotal;
    public Date purchaseDate;
    
    public String warehouseName;
    
    public void setPurchasing(
      int id,
      String purchaseNumber,
      int qtyTotal,
      int subtotal,
      int grandtotal,
      int warehouseId,
      String warehouseName,
      Date purchaseDate
    ) {
        this.id = id;
        this.purchaseNumber = purchaseNumber;
        this.qtyTotal = qtyTotal;
        this.subtotal = subtotal;
        this.grandtotal = grandtotal;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.purchaseDate = purchaseDate;
    }
    
    public void createPurchasing(String purchaseNumber,int warehouseId,Date purchaseDate){
        this.purchaseNumber = purchaseNumber;
        this.warehouseId = warehouseId;
        this.purchaseDate = purchaseDate;
    }
}