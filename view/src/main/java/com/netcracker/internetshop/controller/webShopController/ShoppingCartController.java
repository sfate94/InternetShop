package com.netcracker.internetshop.controller.webShopController;

import com.netcracker.internetshop.dao.OrderDAO;
import com.netcracker.internetshop.dao.ToolsDAO;
import com.netcracker.internetshop.dao.Utils;
import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.models.CartInfo;

import com.netcracker.internetshop.models.ToolsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ShoppingCartController {

    @Autowired
    @Qualifier("productDAO")
    private ToolsDAO toolsDAO;

    @Autowired
    @Qualifier("orderDAO")
    private OrderDAO orderDAO;

    @RequestMapping({"/buyProduct"})
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "toolsId", defaultValue = "") String toolsId) {

        Tools tools = null;
        if (toolsId != null && toolsId.length() > 0) {
            tools = toolsDAO.findTools(toolsId);
        }
        if (tools != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ToolsInfo toolsInfo = new ToolsInfo(tools);

            cartInfo.addTools(toolsInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    @RequestMapping({"/shoppingCartRemoveProduct"})
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "toolsId", defaultValue = "") String toolsId) {
        Tools tools = null;
        if (toolsId != null) {
            tools = toolsDAO.findTools(toolsId);
        }
        if (tools != null) {

            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ToolsInfo toolsInfo = new ToolsInfo(tools);

            cartInfo.removeTools(toolsInfo);

        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // POST: Update quantity of products in cart.
    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // GET: Show Cart
    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }


}
