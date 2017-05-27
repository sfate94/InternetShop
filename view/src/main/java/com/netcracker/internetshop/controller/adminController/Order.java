package com.netcracker.internetshop.controller.adminController;

import com.netcracker.internetshop.dao.OrderDAO;
import com.netcracker.internetshop.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Order {

    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);


    @Autowired
    @Qualifier("orderDAO")
    private OrderDAO orderDAO;


    @RequestMapping(value = {"/orderList"}, method = RequestMethod.GET)
    public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_MANAGER")) {
                paginationResult = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
                break;
            }
        }
        if (paginationResult == null) {
            paginationResult = orderDAO.listOrderInfo(userDetails.getUsername(), page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        }

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }


    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);

        model.addAttribute("orderInfo", orderInfo);

        return "order";
    }

}
