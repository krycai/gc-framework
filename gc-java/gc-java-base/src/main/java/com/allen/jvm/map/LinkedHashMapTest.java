package com.allen.jvm.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xuguocai on 2020/12/18 14:21
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("1",1) ;
        map.put("2",2) ;
        map.put("3",3) ;
        map.put("4",4) ;
        map.put("5",5) ;
        System.out.println(map.toString());
    }
}
