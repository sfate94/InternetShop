package Models;

import Entity.catalog.Tools;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ToolsInfo {
    private String toolsId;
    private String modelId;
    private String typeId;
    private int cost;

    private boolean newTools=false;

    // Upload file.
    private CommonsMultipartFile fileData;

    public ToolsInfo(String s, String getmodelId, String gettypeId, int getcost) {
    }

    public ToolsInfo(Tools tools) {
        this.toolsId = tools.gettoolsId();
        this.modelId = tools.getmodelId();
        this.typeId = tools.gettypeId();
        this.cost = tools.getcost();
    }

    public ToolsInfo(String code, String name, double price) {
        this.toolsId = toolsId;
        this.modelId = modelId;
        this.typeId = typeId;
        this.cost = cost;
    }

    public String gettoolsId() {
        return toolsId;
    }

    public void settoolsId(String toolsId) {
        this.toolsId = toolsId;
    }

    public String getmodelId() {
        return modelId;
    }

    public void setmodelId(String modelId) {
        this.modelId = modelId;
    }

    public String gettypeId() {
        return typeId;
    }

    public void settypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getcost() {
        return cost;
    }

    public void setcost(int cost) {
        this.cost = cost;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public boolean isnewTools() {
        return newTools;
    }

    public void setnewTools(boolean newProduct) {
        this.newTools = newTools;
    }

}