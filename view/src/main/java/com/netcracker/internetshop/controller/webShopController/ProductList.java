package com.netcracker.internetshop.controller.webShopController;

import com.netcracker.internetshop.dao.ToolsDAO;
import com.netcracker.internetshop.dao.TypeDAO;
import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.models.ToolsInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductList {

    @Autowired
    @Qualifier("productDAO")
    private ToolsDAO toolsDAO;

    @Autowired
    @Qualifier("typeDAO")
    private TypeDAO typeDAO;

    @RequestMapping(value = {"productList"}, method = RequestMethod.GET)
    public String listProductHandler(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "typeId", required = false) String typeId,
                                     @RequestParam(value = "maxResult", defaultValue = "3") int maxResult) {
        List<Tools> result = StringUtils.isEmpty(typeId) ? toolsDAO.queryTools(page, maxResult) : toolsDAO.queryToolsByTypeId(page, maxResult, typeId);
        model.addAttribute("count", StringUtils.isEmpty(typeId) ? toolsDAO.getCount().intValue() : toolsDAO.getCountByTypeId(typeId));
        model.addAttribute("typeId", typeId);
        model.addAttribute("tools", convertResultToModel(result));
        model.addAttribute("page", page);
        model.addAttribute("maxResult", maxResult);
        if (typeId == null) model.addAttribute("types", typeDAO.getTypeTools());
        return typeId == null ? "catalog" : "products";
    }

    private List<ToolsInfo> convertResultToModel(List<Tools> result) {
        List<ToolsInfo> toolsInfos = new ArrayList<>();
        for (Tools tools : result) {
            ToolsInfo tool = new ToolsInfo(tools);
            toolsInfos.add(tool);
        }
        return toolsInfos;
    }

    @RequestMapping(value = {"/productImage"}, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("toolsId") String toolsId) throws IOException {
        Tools tools = null;
        if (toolsId != null) {
            tools = this.toolsDAO.findTools(toolsId);
        }
        if (tools != null && tools.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(tools.getImage());
        }
        response.getOutputStream().close();
    }
}
