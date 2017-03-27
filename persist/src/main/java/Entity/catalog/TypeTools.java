package entity.catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Type_Tools", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class TypeTools implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    private String typeId;
    private String typeName;


    @Id
    @Column(name = "Type_ID", length = 50)
    public String gettypeId() {
        return typeId;
    }

    public void settypeId(String typeId) {
        this.typeId = typeId;
    }
    @Column(name = "TypeName",length = 150, nullable = false)
    public String gettypeName() {
        return typeName;
    }

    public void settypeName(String typeName) {
        this.typeName = typeName;
    }


}
