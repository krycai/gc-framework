package com.allen.sharding.controller;

import com.allen.sharding.mapper.UserMapper;
import com.allen.sharding.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by xuguocai on 2020/12/24 11:15
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i <1000 ; i++) {
            User user=new User();
            user.setName("test"+i);
            user.setCityId(1%2==0?1:2);
            user.setCreateTime(new Date());
            user.setSex(i%2==0?1:2);
            user.setPhone("11111111"+i);
            user.setEmail("xxxxx");
            user.setCreateTime(new Date());
            user.setPassword("eeeeeeeeeeee");
            userMapper.insert(user);
        }

        return "success";
    }

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        User user =  userMapper.selectByPrimaryKey(id);
        System.out.println(user.getId());
        return user;
    }

}
