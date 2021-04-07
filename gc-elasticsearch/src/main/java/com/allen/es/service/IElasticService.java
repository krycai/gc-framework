package com.allen.es.service;

import com.allen.es.po.DocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xuguocai on 2021/4/7 10:44
 */
public interface IElasticService {

    /**
     * 创建索引
     */
    void createIndex();

    /**
     * 删除索引
     * @param index
     */
    void deleteIndex(String index);

    /**
     * 保存数据
     * @param docBean
     */
    void save(DocBean docBean);

    /**
     * 批量保存
     * @param list
     */
    void saveAll(List<DocBean> list);

    /**
     * 查找所有
     * @return
     */
    Iterator<DocBean> findAll();


}
