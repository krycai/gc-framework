package com.allen.es.repository;

import com.allen.es.po.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by xuguocai on 2021/4/7 10:42
 */
public interface ElasticRepository extends ElasticsearchRepository<DocBean, Long> {

}
