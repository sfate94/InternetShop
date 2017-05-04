package com.netcracker.internetshop.entity.catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "model", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "Model_ID") })
public class Model implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String modelId;
    private String modelName;
    private String typeId;


    @Id
    @Column(name = "Model_ID", length = 50)
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Column(name = "Model_Name",length = 150, nullable = false)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Column(name = "Type_ID", length = 50, nullable = false)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}

