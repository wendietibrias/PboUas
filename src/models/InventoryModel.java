/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author deadg
 */
public class InventoryModel {
    private String productSKU;
    private String productName;
    private String warehouseName;
    public int warehouseId;
    public int availableQty;
    public int productId;
    public int id;
    
    public InventoryModel() {}
    
    public InventoryModel(int availableQty, String productSKU, String productName, String warehouseName) {
        this.availableQty = availableQty;
        this.productSKU = productSKU;
        this.productName = productName;
        this.warehouseName = warehouseName;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    
    public void setInventory(int id,int warehouseId,int availableQty, int productId){
        this.id = id;
        this.warehouseId = warehouseId;
        this.availableQty = availableQty;
        this.productId = productId;
    }
    
    public void createInventory(int warehouseId,int availableQty,int productId){
        this.warehouseId = warehouseId;
        this.availableQty = availableQty;
        this.productId = productId;
    }
}
