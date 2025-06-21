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
    public String purchaseNumber;
    public int subtotal;
    public int grandtotal;
    public int warehouseId;
    public int qtyTotal;
    
    public void createPurchasing(String purchaseNumber){
        this.purchaseNumber = purchaseNumber;
    }
}
