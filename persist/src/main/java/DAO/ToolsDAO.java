package DAO;

import Entity.catalog.Tools;
import Models.PaginationResult;
import Models.ToolsInfo;

public interface ToolsDAO {



    public Tools findTools(String toolsId);

    public ToolsInfo findToolsInfo(String toolsId) ;


    public PaginationResult<ToolsInfo> queryProducts(int page, int maxResult, int maxNavigationPage  );

    public PaginationResult<ToolsInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

    public void save(ToolsInfo productInfo);

}