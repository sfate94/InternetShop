package models;

import entity.catalog.TypeTools;

public class TypeToolsInfo {
    private String typeId;
    private String typeName;

    public TypeToolsInfo() {
    }
    public TypeToolsInfo(TypeTools typeTools) {
        this.typeId = typeTools.getTypeId();
        this.typeName = typeTools.getTypeName();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
