package com.netcracker.internetshop.models;

public class CartLineInfo {

    private ToolsInfo toolsInfo;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public ToolsInfo getToolsInfo() {
        return toolsInfo;
    }

    public void setToolsInfo(ToolsInfo toolsInfo) {
        this.toolsInfo = toolsInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.toolsInfo.getCost() * this.quantity;
    }

}