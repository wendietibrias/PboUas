/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Windows 10
 */
public class PurchasingDetailModel {
    public int purchasingId;
    public int subtotal;
    public int qty;
    public int productId;
    public int price;
    public int grandtotal;
   
    public void createPurchasingDetail(
       int subtotal,
       int qty,
       int productId,
       int price,
       int grandtotal,
       int purchaseId
    ){
       this.subtotal = subtotal;
       this.qty = qty;
       this.price = price;
       this.grandtotal = grandtotal;
       this.productId = productId;
       this.purchasingId = purchaseId;
    }
    
}