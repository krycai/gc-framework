package com.allen.es;

import com.allen.es.po.DocBean;
import com.allen.es.service.IElasticService;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
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

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

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

		DocBean docBean = new DocBean();
		docBean.setId(6L);
		String ems = elasticsearchTemplate.delete(docBean, IndexCoordinates.of("allen"));
		System.out.println(ems);
	}

	@Test
	void search() {
		MatchQueryBuilder builder = matchQuery("id", 5L);
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
		MatchQueryBuilder builder = matchQuery("firstCode", "XX0193hhh");
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
//		QueryStringQueryBuilder content = QueryBuilders.queryStringQuery("999");
//		Query searchQuery = new NativeSearchQuery(content).setPageable(PageRequest.of(0, 10));

		// 单字符串全文查询
		//Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("888")).withPageable(PageRequest.of(0, 10)).build();
		// 指定字段模糊查询
		//Query searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("content", "content")).withPageable(PageRequest.of(0, 10)).build();
		// 指定字段短语匹配
//		Query searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("content", "allen")).withPageable(PageRequest.of(0, 10)).build();
		// 完全匹配查询
		//Query searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("content", "allen")).withPageable(PageRequest.of(0, 10)).build();

		// 多字段匹配某一个值
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		// MatchQueryBuilder.Operator.AND 完全匹配到短语的才查询出来，比如 张三，只有张三都出现，才匹配到
		queryBuilder.must(QueryBuilders.multiMatchQuery("XX0193", "firstCode", "secordCode", "content").operator(MatchQueryBuilder.DEFAULT_OPERATOR.AND));
		// 构建查询语句,默认查询10条，这里如果使用ES作为搜索，需要考虑分页的情况
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<DocBean> search = elasticsearchTemplate.search(searchQuery, DocBean.class,IndexCoordinates.of("allen"));

		long totalHits = search.getTotalHits();
		System.out.println(totalHits);
		List<SearchHit<DocBean>> searchHits = search.getSearchHits();

		if (searchHits != null){
			for (SearchHit<DocBean> item:searchHits){
				DocBean docBean = item.getContent();
				System.out.println(docBean);
			}
		}
	}

	/**
	 * BoolQuery，设置多个条件的查询方式，作用是组合多个Query，有四种组合方式 must，mustnot，filter，should ，query查询的时候，会先比较查询条件，然后计算分值，最后返回文档结果；
	 *
	 * must 相当于 与
	 *
	 *    跟mysql的 and作用一样，会参与计算分值
	 *
	 * mustnot 相当于 非
	 *
	 *    跟mysql的not and作用一样，不会参与计算分值
	 *
	 * should 相当于 或
	 *
	 *    跟mysql的 or作用一样，有一个或者多个should子句，那么只要满足一个就可以返回。minimum_should_match参数定义了至少满足几个子句。
	 *
	 * filter 过滤
	 *
	 * 排除掉不需要的数据
	 *
	 *    合并查询
	 */
	@Test
	void searchUnion() {
		// 构建查询
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		// and  match query搜索的时候，首先会解析查询字符串，进行分词，然后查询，而term query,输入的查询内容是什么，就会按照什么去查询，并不会解析查询内容，对它分词。
		queryBuilder.must(QueryBuilders.termQuery("content","saveAll"));
		// or
//		queryBuilder.should(QueryBuilders.matchQuery("content","saveAll"));

		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<DocBean> search = elasticsearchTemplate.search(searchQuery, DocBean.class,IndexCoordinates.of("allen"));

		long totalHits = search.getTotalHits();
		System.out.println(totalHits);
		List<SearchHit<DocBean>> searchHits = search.getSearchHits();

		if (searchHits != null){
			for (SearchHit<DocBean> item:searchHits){
				DocBean docBean = item.getContent();
				System.out.println(docBean);
			}
		}
	}
}
