package entity.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tools", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "Tools_ID") })
public class Tools implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String toolsId;
    private String modelId;
    private String typeId;
    private  Model model;
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
    @Column(name = "Model_ID", length = 50,nullable = false)
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
    @Column(name = "Type_ID", length = 50,nullable = false)
    public String getTypeId() {
        return typeId;
    }

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Model_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "Model_ID"))
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }*/

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
}
