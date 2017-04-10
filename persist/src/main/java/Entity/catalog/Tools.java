package entity.catalog;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Model", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class Tools implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String toolsId;
    private String modelId;
    private String typeId;
    private int cost;
    private int length;
    private int height;
    private int weight;
    private int power;
    private int speed;
    private byte[] image;

    @Id
    @Column(name = "Tools_ID", length = 50)
    public String gettoolsId() {
        return toolsId;
    }

    public void settoolsId(String toolsId) {
        this.toolsId = toolsId;
    }
    @Column(name = "Model_ID", length = 50,nullable = false)
    public String getmodelId() {
        return modelId;
    }

    public void setmodelId(String modelId) {
        this.modelId = modelId;
    }
    @Column(name = "Type_ID", length = 50,nullable = false)
    public String gettypeId() {
        return typeId;
    }

    public void settypeId(String typeId) {
        this.typeId = typeId;
    }
    @Column(name = "Cost", nullable = false)
    public int getcost() {
        return cost;
    }

    public void setcost(int cost) {
        this.cost = cost;
    }
    @Column(name = "Lenght", nullable = false)
    public int getlenght() {
        return length;
    }

    public void setlenght(int lenght) {
        this.length = lenght;
    }
    @Column(name = "Height", nullable = false)
    public int getheight() {
        return height;
    }

    public void setheight(int height) {
        this.height = height;
    }
    @Column(name = "Weight", nullable = false)
    public int getweight() {
        return weight;
    }

    public void setweight(int weight) {
        this.weight = weight;
    }
    @Column(name = "Power", nullable = false)
    public int getpower() {
        return power;
    }

    public void setpower(int power) {
        this.power = power;
    }
    @Column(name = "Speed", nullable = false)
    public int getspeed() {
        return speed;
    }

    public void setspeed(int speed) {
        this.speed = speed;
    }

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    }
