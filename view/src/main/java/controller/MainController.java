package controller;

import dao.OrderDAO;
import dao.ToolsDAO;
import dao.ToolsDAOImpl;
import dao.Utils;
import entity.catalog.Tools;
import models.CartInfo;
import models.CustomerInfo;
import models.PaginationResult;
import models.ToolsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validator.CustomerInfoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

   @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ToolsDAO toolsDAO;

    /*@Autowired
    private CustomerInfoValidator customerInfoValidator;*/

   /* @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {

        }
        // For Customer Form.
         //@ModelAttribute("customerForm") @Validated CustomerInfo
         //customerForm)
        else if (target.getClass() == CustomerInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }

    }*/

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
                                     @RequestParam(value = "name", defaultValue = "") String likeName,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
       final int maxResult = 5;
        final int maxNavigationPage = 10;

        List<Tools> result = toolsDAO.queryTools(page,
                maxResult, maxNavigationPage, likeName);
        List<ToolsInfo> toolsInfos = new ArrayList<>();
        for (Tools tools: result){
            ToolsInfo tool = new ToolsInfo(tools);
            toolsInfos.add(tool);
        }
        model.addAttribute("paginationProducts", toolsInfos);
        return "productList";
    }
    @RequestMapping({ "/buyProduct" })
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

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "toolsId", defaultValue = "") String toolsId) {
        Tools tools = null;
        if (tools != null) {
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
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

   // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart is empty.
        if (cartInfo.isEmpty()) {

            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }

        model.addAttribute("customerForm", customerInfo);

        return "shoppingCartCustomer";
    }

    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
                                           BindingResult result, //
                                           final RedirectAttributes redirectAttributes) {

        // If has Errors.
        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward to reenter customer info.
            return "shoppingCartCustomer";
        }

        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);

        cartInfo.setCustomerInfo(customerForm);

        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }

    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }

        return "shoppingCartConfirmation";
    }

    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            return "shoppingCartConfirmation";
        }
        // Remove Cart In Session.
        Utils.removeCartInSession(request);

        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);

        // Redirect to successful page.
        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }

        return "shoppingCartFinalize";
    }

    /*@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("toolsId") String toolsId) throws IOException {
        Tools tools = null;
        if (tools != null) {
            tools = this.toolsDAO.findTools(toolsId);
        }
        *//*if (tools != null && tools.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(tools.getImage());
        }*//*
        response.getOutputStream().close();
    }*/

}


