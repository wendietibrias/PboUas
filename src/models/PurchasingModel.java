/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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
    
    public void setPurchasing(
      int id,
      String purchaseNumber,
      int qtyTotal,
      int subtotal,
      int grandtotal,
      int warehouseId
    ) {
        this.purchaseNumber = purchaseNumber;
        this.qtyTotal = qtyTotal;
        this.subtotal = subtotal;
        this.grandtotal = grandtotal;
        this.warehouseId = warehouseId;
    }
    
    public void createPurchasing(String purchaseNumber,int warehouseId){
        this.purchaseNumber = purchaseNumber;
        this.warehouseId = warehouseId;
    }
}