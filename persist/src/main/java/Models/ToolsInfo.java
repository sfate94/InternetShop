package models;

import entity.catalog.Tools;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
public class ToolsInfo {
    private String toolsId;
    private String modelId;
    private String typeId;
    private int cost;
    private int length;
    private int height;
    private int weight;
    private int power;
    private int speed;
    private boolean newTools=false;

    // Upload file.
    private CommonsMultipartFile fileData;

    public ToolsInfo(Tools tools) {
        this.toolsId = tools.getToolsId();
        this.modelId = tools.getModelId();
        this.typeId = tools.getTypeId();
        this.cost = tools.getCost();
        this.length = tools.getLength();
        this.height = tools.getHeight();
        this.weight = tools.getWeight();
        this.power = tools.getPower();
        this.speed = tools.getSpeed();
    }

    public ToolsInfo(String toolsId, String modelId, String typeId, int cost, int length, int height, int weight, int power, int speed) {
        this.toolsId = toolsId;
        this.modelId = modelId;
        this.typeId = typeId;
        this.cost = cost;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.power = power;
        this.speed = speed;
    }

    public ToolsInfo() {

    }

    public String getToolsId() {
        return toolsId;
    }

    public void setToolsId(String toolsId) {
        this.toolsId = toolsId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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

    public void setnewTools(boolean newTools) {
        this.newTools = newTools;
    }

    public int getLenght() {
        return length;
    }

    public void setLenght(int lenght) {
        this.length = lenght;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}