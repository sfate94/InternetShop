package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.models.CartInfo;
import com.netcracker.internetshop.models.OrderDetailInfo;
import com.netcracker.internetshop.models.OrderInfo;
import com.netcracker.internetshop.models.PaginationResult;

import java.util.List;

public interface OrderDAO {

    public void saveOrder(CartInfo cartInfo);

    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

    public PaginationResult<OrderInfo> listOrderInfo(String userName, int page, int maxResult, int maxNavigationPage);

    public OrderInfo getOrderInfo(String orderId);

    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}