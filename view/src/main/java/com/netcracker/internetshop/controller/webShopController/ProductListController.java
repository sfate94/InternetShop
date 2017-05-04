package com.netcracker.internetshop.controller.webShopController;

import com.netcracker.internetshop.dao.ToolsDAO;
import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.models.ToolsInfo;
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
public class ProductListController {

    @Autowired
    @Qualifier("productDAO")
    private ToolsDAO toolsDAO;

    @RequestMapping({"/productList"})
    public String listProductHandler(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "maxResult", defaultValue = "6") int maxResult) {
        int count = toolsDAO.getCount().intValue();
        List<Tools> result = toolsDAO.queryTools(page, maxResult);
        List<ToolsInfo> toolsInfos = new ArrayList<>();
        for (Tools tools : result) {
            ToolsInfo tool = new ToolsInfo(tools);
            toolsInfos.add(tool);
        }
        model.addAttribute("paginationProducts", toolsInfos);
        model.addAttribute("page", page);
        model.addAttribute("maxResult", maxResult);
        model.addAttribute("count", count);
        return "productList";
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
