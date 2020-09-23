package com.allen.mycat.controller;

import com.allen.mycat.mapper.TravelrecordMapper;
import com.allen.mycat.po.Travelrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuguocai 2020/9/23 15:56
 */
@RestController
@CrossOrigin
@RequestMapping("/mycat")
public class TravelrecordController {

    @Autowired
    private TravelrecordMapper travelrecordMapper;

    @GetMapping(value = "/insert")
    public String insert() {
        Travelrecord travelrecord = new Travelrecord();
        travelrecord.setId(4);
        travelrecord.setName("\uD83D\uDCAA\uD83D\uDC48\uD83D\uDE4D\uD83D\uDE4E\uD83D\uDE36\uD83D\uDCF1\uD83D\uDCF2");

        travelrecordMapper.insertSelective(travelrecord);
        return "5555";
    }

}

