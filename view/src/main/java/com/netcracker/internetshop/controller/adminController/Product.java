package com.netcracker.internetshop.controller.adminController;

import com.google.gson.Gson;
import com.netcracker.internetshop.dao.ModelDAO;

import com.netcracker.internetshop.dao.ToolsDAO;
import com.netcracker.internetshop.dao.TypeDAO;
import com.netcracker.internetshop.entity.catalog.TypeTools;
import com.netcracker.internetshop.models.*;

import com.netcracker.internetshop.validator.ProductInfoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Product {

    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);

    @Autowired
    @Qualifier("productDAO")
    private ToolsDAO toolsDAO;

    @Autowired
    @Qualifier("typeDAO")
    private TypeDAO typeDAO;

    @Autowired
    @Qualifier("modelDAO")
    private ModelDAO modelDAO;

    @Autowired
    @Qualifier("productInfoValidator")
    private ProductInfoValidator productInfoValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ToolsInfo.class) {
            dataBinder.setValidator(productInfoValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "toolsId", defaultValue = "") String toolsId) {
        ToolsInfo toolsInfo = null;

        if (toolsId != null && toolsId.length() > 0) {
            toolsInfo = toolsDAO.findToolsInfo(toolsId);
        }
        if (toolsInfo == null) {
            toolsInfo = new ToolsInfo();
            toolsInfo.setnewTools(true);
        }
        List<TypeToolsInfo> typeToolsInfoList = getTypes();
        List<DeviceModel> deviceModels = new ArrayList<>();
        if (!typeToolsInfoList.isEmpty()) {
            deviceModels = getModels(typeToolsInfoList.get(0).getTypeId());
        }
        model.addAttribute("productForm", toolsInfo);
        model.addAttribute("types", typeToolsInfoList);
        model.addAttribute("models", deviceModels);
        return "product";
    }

    private List<TypeToolsInfo> getTypes() {
        List<TypeTools> typeToolsList = typeDAO.getTypeTools();
        List<TypeToolsInfo> typeToolsInfos = new ArrayList<>();
        for (TypeTools typeTools : typeToolsList) {
            TypeToolsInfo typeToolsInfo = new TypeToolsInfo(typeTools);
            typeToolsInfos.add(typeToolsInfo);
        }
        return typeToolsInfos;
    }

    private List<DeviceModel> getModels(String typeId) {
        List<com.netcracker.internetshop.entity.catalog.Model> models = modelDAO.getModelsByTypeId(typeId);
        List<DeviceModel> deviceModels = new ArrayList<>();
        for (com.netcracker.internetshop.entity.catalog.Model model : models) {
            DeviceModel deviceModel = new DeviceModel(model);
            deviceModels.add(deviceModel);
        }
        return deviceModels;
    }

    @RequestMapping(value = {"models"}, method = RequestMethod.GET)
    public void product(HttpServletResponse response, @RequestParam(value = "typeId", defaultValue = "") String typeId) {
        try (Writer writer = response.getWriter()) {
            Gson gson = new Gson();
            writer.append(gson.toJson(getModels(typeId)));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    // POST: Save product  "/product/{id}"
    @RequestMapping(value = {"/product"}, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model, //
                              @ModelAttribute("productForm") @Validated ToolsInfo toolsInfo, //
                              BindingResult result, //
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "product";
        }
        try {
            toolsDAO.save(toolsInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            // Need: Propagation.NEVER?
            String message = e.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "product";

        }
        return "redirect:/productList";
    }

}
