package com.netcracker.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {

    private int orderNum;

    private CustomerInfo customerInfo;

    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }

    private CartLineInfo findLineByCode(String toolsId) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getToolsInfo().getToolsId().equals(toolsId)) {
                return line;
            }
        }
        return null;
    }

    public void addTools(ToolsInfo toolsInfo, int quantity) {
        CartLineInfo line = this.findLineByCode(toolsInfo.getToolsId());

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setToolsInfo(toolsInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateTools(String toolsId, int quantity) {
        CartLineInfo line = this.findLineByCode(toolsId);

        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeTools(ToolsInfo toolsInfo) {
        CartLineInfo line = this.findLineByCode(toolsInfo.getToolsId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateTools(line.getToolsInfo().getToolsId(), line.getQuantity());
            }
        }

    }

}