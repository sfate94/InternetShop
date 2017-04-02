package dao;

import entity.catalog.Tools;
import models.PaginationResult;
import models.ToolsInfo;

public interface ToolsDAO {



    Tools findTools(String toolsId);

    ToolsInfo findToolsInfo(String toolsId) ;


    public PaginationResult<ToolsInfo> queryTools(int page, int maxResult, int maxNavigationPage  );

    public PaginationResult<ToolsInfo> queryTools(int page, int maxResult, int maxNavigationPage, String likeName);

    public void save(ToolsInfo toolsInfo);

}