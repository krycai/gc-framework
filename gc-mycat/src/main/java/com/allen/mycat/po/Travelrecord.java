package com.allen.mycat.po;

/**
 * @author xuguocai 2020/9/23 15:23
 */
public class Travelrecord {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Travelrecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
