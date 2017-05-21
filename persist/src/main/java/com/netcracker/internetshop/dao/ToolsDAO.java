package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.models.ToolsInfo;

import java.util.List;

public interface ToolsDAO {


    Tools findTools(String toolsId);

    ToolsInfo findToolsInfo(String toolsId);

    List<Tools> queryTools(int page, int maxResult);

    void save(ToolsInfo toolsInfo);

    Long getCount();

    List<Tools> getToolsByTypeId(String typeId);
}