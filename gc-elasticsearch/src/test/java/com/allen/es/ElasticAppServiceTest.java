package com.allen.es;

import com.allen.es.po.DocBean;
import com.allen.es.repository.ElasticRepository;
import com.allen.es.service.IElasticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class ElasticAppServiceTest {

	@Autowired
	private IElasticService iElasticService;
	@Autowired
	private ElasticRepository elasticRepository;

	private Pageable pageable = PageRequest.of(0,10);
	/**
	 * 保存单个实体
	 */
	@Test
	void save() {
		DocBean docBean = new DocBean(19997L, "XX0193hhh", "XX8064hhh", "xxxxxxgghhhh", 1);
		iElasticService.save(docBean);
	}

	/**
	 * 批量处理
	 */
	@Test
	void saveAll() {
		List<DocBean> list = new ArrayList<>();
		list.add(new DocBean(19998L, "XX0193", "XX8064", "saveAll", 2));
		list.add(new DocBean(19999L, "XX0193", "XX8064", "saveAll", 2));
		list.add(new DocBean(20001L, "XX0193", "XX8064", "saveAll", 2));
		list.add(new DocBean(20002L, "XX0193", "XX8064", "saveAll", 2));
		list.add(new DocBean(20003L, "XX0193", "XX8064", "saveAll", 2));

		iElasticService.saveAll(list);
	}

	@Test
	void deleteIndex(){
		iElasticService.deleteIndex("ems");
	}

	@Test
	void findAll(){

		Page<DocBean> byContent = elasticRepository.findAll(pageable);
		System.out.println(byContent);

		Stream<DocBean> docBeanStream = byContent.get();
		docBeanStream.forEach(item ->{
			System.out.println("实体===="+item);
		});

		System.out.println(byContent.getTotalPages());
	}

	@Test
	void findById(){
		Optional<DocBean> byId = elasticRepository.findById(9999L);

		System.out.println(byId.get());
	}
}
