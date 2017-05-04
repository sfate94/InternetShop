package com.netcracker.internetshop.entity.catalog;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tools", //
        uniqueConstraints = {@UniqueConstraint(columnNames = "Tools_ID")})
public class Tools implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String toolsId;
    private TypeTools typeTools;
    private Model model;
    private int cost;
    private int length;
    private int height;
    private int weight;
    private int power;
    private int speed;
    private byte[] image;

    @Id
    @Column(name = "Tools_ID", length = 50)
    public String getToolsId() {
        return toolsId;
    }

    public void setToolsId(String toolsId) {
        this.toolsId = toolsId;
    }

    @OneToOne
    @JoinColumn(name = "Type_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "Type_ID"))
    public TypeTools getTypeTools() {
        return typeTools;
    }

    public void setTypeTools(TypeTools typeTools) {
        this.typeTools = typeTools;
    }


    @OneToOne
    @JoinColumn(name = "Model_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "Model_ID"))
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }


    @Column(name = "Cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Column(name = "Length", nullable = false)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Column(name = "Height", nullable = false)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Column(name = "Weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Column(name = "Power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "Speed", nullable = false)
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Column(name = "Image", columnDefinition = "bytea")
    @Lob
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
