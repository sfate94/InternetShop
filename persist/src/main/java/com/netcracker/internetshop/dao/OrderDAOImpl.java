package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.entity.order.Order;
import com.netcracker.internetshop.entity.orderDetail.OrderDetail;
import com.netcracker.internetshop.models.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ToolsDAO toolsDAO;

    private int getMaxOrderNum() {
        String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }

    @Override
    public void saveOrder(CartInfo cartInfo) {
        Session session = sessionFactory.getCurrentSession();

        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();

        order.setId(UUID.randomUUID().toString());
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());
        order.setCustomerAddress(customerInfo.getAddress());

        session.persist(order);

        List<CartLineInfo> lines = cartInfo.getCartLines();

        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getToolsInfo().getCost());
            detail.setQuantity(line.getQuantity());

            String toolsId = line.getToolsInfo().getToolsId();
            Tools tools = this.toolsDAO.findTools(toolsId);
            detail.setTools(tools);

            session.persist(detail);
        }

        cartInfo.setOrderNum(orderNum);
    }

    // @page = 1, 2, ...
    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);

        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(String userName, int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + Order.class.getName() + " ord where ord.customerName=?"
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter(0, userName);

        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }

    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(), //
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.tools.toolsId, d.tools.model.modelName , d.quantity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);

        return query.list();
    }

}
