package com.netcracker.internetshop.models;

import com.netcracker.internetshop.entity.catalog.Tools;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.UnsupportedEncodingException;

public class ToolsInfo {
    private String toolsId;
    private DeviceModel deviceModel;
    private TypeToolsInfo typeToolsInfo;
    private String typeId;
    private int cost;
    private int length;
    private int height;
    private int weight;
    private int power;
    private int speed;
    private boolean newTools=false;
    private byte[] image;
    private String base64Image;

    // Upload file.
    private CommonsMultipartFile fileData;

    public ToolsInfo(Tools tools) {
        this.toolsId = tools.getToolsId();
        this.typeToolsInfo = new TypeToolsInfo(tools.getTypeTools());
        this.deviceModel = new DeviceModel(tools.getModel());
        this.cost = tools.getCost();
        this.length = tools.getLength();
        this.height = tools.getHeight();
        this.weight = tools.getWeight();
        this.power = tools.getPower();
        this.speed = tools.getSpeed();
        this.image = tools.getImage();
        try {
            this.base64Image = new String(Base64.encodeBase64(this.image), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ToolsInfo() {
    }

    public String getToolsId() {
        return toolsId;
    }

    public void setToolsId(String toolsId) {
        this.toolsId = toolsId;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    public TypeToolsInfo getTypeToolsInfo() {
        return typeToolsInfo;
    }

    public void setTypeToolsInfo(TypeToolsInfo typeToolsInfo) {
        this.typeToolsInfo = typeToolsInfo;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}