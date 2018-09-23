package com.xyg.solr.single;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 根据某个索引id删除一条索引 和 根据一批索引id删除一批索引
 */
public class DeleteIndex {


    @Test
    public void deleteIndexById() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //全删
        //solrClient.deleteByQuery("*:*");
        //模糊匹配删除
        solrClient.deleteByQuery("name:红");
        //指定id删除
        //solrClient.deleteById("1");
        solrClient.commit();
    }

    @Test
    public void deleteIndexByListId() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //通过id删除
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("2");
        ids.add("3");
        solrClient.deleteById(ids);
        //[3]提交
        solrClient.commit();
        //[4]关闭资源
        solrClient.close();
    }


}
