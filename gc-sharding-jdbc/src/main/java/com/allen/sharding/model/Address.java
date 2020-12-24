package com.allen.sharding.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * Created by xuguocai on 2020/12/24 11:11
 */
@Data
@Table(name="t_address")
public class Address {
    private Long id;
    private String code;
    private String name;
    private String pid;
    private Integer type;
    private Integer lit;
}
