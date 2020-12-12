package com.allen.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 重点在于  ConfigurationProperties 运用
 *
 * Created by xuguocai on 2020/11/30 14:44
 */

@Component
@ConfigurationProperties(prefix = "allen")
public class AllenProperties {
    private String name;
    private int money;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AllenProperties{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", sex='" + sex + '\'' +
                '}';
    }
}
