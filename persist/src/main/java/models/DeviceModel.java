package models;

import entity.catalog.Model;

public class DeviceModel {
    private String modelId;
    private String modelName;
    private String typeId;

    public DeviceModel() {
    }
    public DeviceModel(Model model) {
        this.modelId = model.getModelId();
        this.modelName = model.getModelName();
        this.typeId = model.getTypeId();
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
