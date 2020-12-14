package com.allen.upload.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferenceUtils {


    private DifferenceUtils(){

    }
    /**
     * 比较2个list找到相同的部分
     *
     * @param allOpenidList
     * @param dbOpenidList
     * @return
     */
    public static List<String> getNeedAddOpenidList(List<String> allOpenidList, List<String> dbOpenidList) {
        if (dbOpenidList != null && !dbOpenidList.isEmpty()) {
            Map<String, String> dataMap = new HashMap<>();
            for (String id : dbOpenidList) {
                dataMap.put(id, id);
            }

            List<String> newList = new ArrayList<>();
            for (String id : allOpenidList) {
                if (dataMap.containsKey(id)) {
                    newList.add(id);
                }
            }
            return newList;
        } else {
            return allOpenidList;
        }
    }

    /**
     * 比较2个list找到差异的部分
     *
     * @param allOpenidList
     * @param dbOpenidList
     * @return
     */
    public static List<String> getDifferenceList(List<String> allOpenidList, List<String> dbOpenidList) {
        if (dbOpenidList != null && !dbOpenidList.isEmpty()) {
            Map<String, String> dataMap = new HashMap<>();
            for (String id : dbOpenidList) {
                dataMap.put(id, id);
            }

            List<String> newList = new ArrayList<>();
            for (String id : allOpenidList) {
                if (!dataMap.containsKey(id)) {
                    newList.add(id);
                }
            }
            return newList;
        } else {
            return allOpenidList;
        }
    }
}
