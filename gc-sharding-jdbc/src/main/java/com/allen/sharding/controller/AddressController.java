package com.allen.sharding.controller;

import com.allen.sharding.mapper.AddressMapper;
import com.allen.sharding.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuguocai on 2020/12/24 11:17
 */
@RestController
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("/address/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i <10 ; i++) {
            Address address=new Address();
            address.setCode("code_"+i);
            address.setName("name_"+i);
            address.setPid(i+"");
            address.setType(0);
            address.setLit(i%2==0?1:2);
            addressMapper.insert(address);
        }

        return "success";
    }

    @RequestMapping("/address/get/{id}")
    @ResponseBody
    public Address get(@PathVariable Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
