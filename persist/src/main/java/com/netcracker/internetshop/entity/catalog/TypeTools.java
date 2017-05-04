package com.netcracker.internetshop.entity.catalog;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "type_tools", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "Type_ID") })
public class TypeTools implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String typeId;
    private String typeName;


    @Id
    @Column(name = "Type_ID", length = 50)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    @Column(name = "TypeName",length = 150, nullable = false)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



}
