package com.xyg.solr;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 指定id查询索引 和条件查询
 */
public class FindIndex {

    @Test
    public void findIndex1() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        // 创建搜索对象
        SolrQuery query = new SolrQuery();
        // 设置搜索条件
        query.set("q","*:*");
        //设置每页显示多少条
        query.setRows(2);
        //发起搜索请求
        QueryResponse response = solrClient.query(query);
        // 查询结果
        SolrDocumentList docs = response.getResults();
        // 查询结果总数
        long cnt = docs.getNumFound();
        System.out.println("总条数为"+cnt+"条");
        for (SolrDocument doc : docs) {
          System.out.println("id:"+ doc.get("id") + ",name:"+ doc.get("name") + ",price:"+ doc.get("price"));
        }
        solrClient.close();
    }

    @Test
    public void findIndex2() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //2 封装查询参数
        Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "*:*");
        //3 添加到SolrParams对象,SolrParams 有一个 SolrQuery 子类，它提供了一些方法极大地简化了查询操作
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
        //4 执行查询返回QueryResponse
        QueryResponse response = solrClient.query(queryParams);
        //5 获取doc文档
        SolrDocumentList docs = response.getResults();
        // 查询结果总数
        long cnt = docs.getNumFound();
        System.out.println("总条数为" + cnt + "条");
        //[6]内容遍历
        for (SolrDocument doc : docs) {
            System.out.println("id:" + doc.get("id") + ",name:" + doc.get("name") + ",price:" + doc.get("price"));
        }
        solrClient.close();
    }

}
