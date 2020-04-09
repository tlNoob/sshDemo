package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tl_menu_Info")
public class TLMenuInfo implements Serializable {
   @Id
   private String Id;
   @Column
   private String name;
    @Column
   private String link;
    @Column
   private String parentId;
    @Column
   private String remark;

    private List<TLMenuInfo> childMenu;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TLMenuInfo> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<TLMenuInfo> childMenu) {
        this.childMenu = childMenu;
    }
}
