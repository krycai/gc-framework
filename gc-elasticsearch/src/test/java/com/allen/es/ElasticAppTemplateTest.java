package com.allen.es;

import com.allen.es.po.DocBean;
import com.allen.es.service.IElasticService;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ElasticAppTemplateTest {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchTemplate;

	@Test
	void save() {
		DocBean docBean = new DocBean(null, "XX0193hhh", "XX8064hhh", "xxxxxxgghhhh", 3);

		DocBean save = elasticsearchTemplate.save(docBean);
		System.out.println(save);
	}

	@Test
	void saveAll() {
		List<DocBean> list = new ArrayList<>();
		list.add(new DocBean(30000L, "XX0193", "XX8064", "saveAll", 4));
		list.add(new DocBean(30001L, "XX0193", "XX8064", "saveAll", 4));
		list.add(new DocBean(30002L, "XX0193", "XX8064", "saveAll", 4));
		list.add(new DocBean(30003L, "XX0193", "XX8064", "saveAll", 4));
		list.add(new DocBean(30004L, "XX0193", "XX8064", "saveAll", 4));
		elasticsearchTemplate.save(list);
	}

	@Test
	void update() {

		Document document = Document.create();
		document.append("firstCode","allen-test").append("secordCode","allen-secordCode").append("content","allen-content");

		UpdateQuery query = UpdateQuery.builder("30001").withDocument(document).build();
		IndexCoordinates index = IndexCoordinates.of("allen");

		UpdateResponse update = elasticsearchTemplate.update(query, index);
		UpdateResponse.Result result = update.getResult();

		System.out.println(result);
	}

	@Test
	void delete() {
//		elasticsearchTemplate.delete("7",IndexCoordinates.of("allen"));

		// todo 需要测试具体的实现
//		Criteria criteria =new Criteria("id");
//		Query query = new CriteriaQuery(criteria);
//		elasticsearchTemplate.delete(query,DocBean.class,IndexCoordinates.of("allen"));

		DocBean docBean = new DocBean();
		docBean.setId(6L);
		String ems = elasticsearchTemplate.delete(docBean, IndexCoordinates.of("allen"));
		System.out.println(ems);
	}

	@Test
	void search() {
		MatchQueryBuilder builder = QueryBuilders.matchQuery("id", 5L);
		Query searchQuery = new NativeSearchQuery(builder);
		SearchHits<DocBean> search = elasticsearchTemplate.search(searchQuery, DocBean.class);

		List<SearchHit<DocBean>> searchHits = search.getSearchHits();
		if (searchHits != null){
			for (SearchHit<DocBean> item:searchHits){
				DocBean content = item.getContent();
				System.out.println(content);
			}
		}
	}

	/**
	 * 分页 精确查找
	 */
	@Test
	void searchPage() {
		MatchQueryBuilder builder = QueryBuilders.matchQuery("firstCode", "XX0193hhh");
		Query searchQuery = new NativeSearchQuery(builder).setPageable(PageRequest.of(0, 10));

		SearchHits<DocBean> search = elasticsearchTemplate.search(searchQuery, DocBean.class);

		long totalHits = search.getTotalHits();
		System.out.println(totalHits);
		List<SearchHit<DocBean>> searchHits = search.getSearchHits();

		if (searchHits != null){
			for (SearchHit<DocBean> item:searchHits){
				DocBean content = item.getContent();
				System.out.println(content);
			}
		}
	}

	/**
	 * 模糊查找
	 */
	@Test
	void searchLike() {

	}

}
