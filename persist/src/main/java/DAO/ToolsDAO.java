package dao;

import entity.catalog.Tools;
import models.PaginationResult;
import models.ToolsInfo;

import java.util.List;

public interface ToolsDAO {


    Tools findTools(String toolsId);

    ToolsInfo findToolsInfo(String toolsId);

 /*   public Tools findTools(String toolsId);

    public ToolsInfo findToolsInfo(String toolsId) ;*/

    List<Tools> queryTools(int page,
                               int maxResult, int maxNavigationPage  );

   List<Tools> queryTools(int page, int maxResult,
                                                       int maxNavigationPage, String likeName);

    public void save(ToolsInfo toolsInfo);

 /*   public PaginationResult<ToolsInfo> queryTools(int page, int maxResult, int maxNavigationPage, String likeName);

    *//*@Override
        public void save(ToolsInfo toolsInfo) {

        }

        @Override
        public PaginationResult<ToolsInfo> queryTools(int page, int maxResult, int maxNavigationPage, String likeName) {
            return null;
        }
    *//*
    PaginationResult<ToolsInfo> queryProducts(int page, int maxResult, int maxNavigationPage);*/
}