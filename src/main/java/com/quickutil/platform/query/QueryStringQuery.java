package com.quickutil.platform.query;

import com.google.gson.JsonObject;
import com.quickutil.platform.JsonUtil;

/**
 * @author shijie.ruan
 */
public class QueryStringQuery extends QueryDSL {
	private String defaultField = null;
	private String defaultOperator = "OR";
	private String analyzer = null;
	private Boolean analyzeWildcard = null;
	private String query;

	/**
	 * query string 类型
	 * @param query 查询的语句
	 */
	QueryStringQuery(String query) {
		super("query_string");
		this.query = query;
	}

	public void setBoost(double boost) {
		this.boost = boost;
	}

	/**
	 * 当 query 中没有设置字段时,默认查询的字段,如果不设置, es 默认为 _all
	 * @param defaultField
	 */
	public void setDefaultField(String defaultField) {
		this.defaultField = defaultField;
	}

	/**
	 * query 中文本进行分词后默认的连接符, 比如 capital of Hungary 会被翻译成 capital OR of OR Hungary,
	 * 不设置默认为 OR
	 * @param defaultOperator
	 */
	public void setDefaultOperator(String defaultOperator) {
		this.defaultOperator = defaultOperator;
	}

	/**
	 * 设置分词器, 如果不设置, 默认会使用 elasticsearch.yml 中的默认分词器,
	 * 如果 elasticsearch.yml 中也没有设置, 默认使用 standard analyzer, 它会去掉标点,空格切分,变成小写
	 * @param analyzer
	 */
	public void setAnalyzer(String analyzer) {
		this.analyzer = analyzer;
	}

	/**
	 * 是否允许使用通配符
	 * @param analyzeWildcard
	 */
	public void setAnalyzeWildcard(Boolean analyzeWildcard) {
		this.analyzeWildcard = analyzeWildcard;
	}

	@Override
	public String toJson() {
		JsonObject stringQuery = new JsonObject();
		stringQuery.addProperty("query", query);
		if (null != defaultField) { stringQuery.addProperty("default_field", defaultField); }
		if (null != defaultOperator) { stringQuery.addProperty("default_operator", defaultOperator); }
		if (null != analyzer) { stringQuery.addProperty("analyzer", analyzer); }
		if (null != analyzeWildcard) { stringQuery.addProperty("analyze_wildcard", analyzeWildcard); }
		JsonObject queryDSL = new JsonObject();
		queryDSL.add(type, stringQuery);
		return JsonUtil.toJson(queryDSL);
	}
}