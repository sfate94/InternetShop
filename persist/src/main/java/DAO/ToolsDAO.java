package dao;

import entity.catalog.Tools;
import models.PaginationResult;
import models.ToolsInfo;

public interface ToolsDAO {



    public Tools findTools(String toolsId);

    public ToolsInfo findToolsInfo(String toolsId) ;


    public PaginationResult<ToolsInfo> queryTools(int page,
                                                       int maxResult, int maxNavigationPage  );

    public PaginationResult<ToolsInfo> queryProducts(int page, int maxResult,
                                                       int maxNavigationPage, String likeName);

    public void save(ToolsInfo productInfo);

    PaginationResult<ToolsInfo> queryTools(int page, int maxResult, int maxNavigationPage, String likeName);
}