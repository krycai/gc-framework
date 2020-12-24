package com.allen.sharding.model;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by xuguocai on 2020/12/24 11:07
 */
@Data
@Table(name="t_user")
public class User {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Integer cityId;
    private Date createTime;
    private Integer sex;

}
