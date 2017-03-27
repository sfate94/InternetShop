package models;

public class CartLineInfo {

    private ToolsInfo toolsInfo;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public ToolsInfo getProductInfo() {
        return toolsInfo;
    }

    public void setProductInfo(ToolsInfo productInfo) {
        this.toolsInfo = productInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.toolsInfo.getcost() * this.quantity;
    }

}