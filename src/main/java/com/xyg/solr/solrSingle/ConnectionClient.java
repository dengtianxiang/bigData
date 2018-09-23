package com.xyg.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.junit.Test;


/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 测试连接客户端
 */
public class ConnectionClient {

    @Test
    public void connectionClient(){
        //设置solr客户端url地址
        String solrUrl = "http://node21:8080/solr/new_core";
        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        System.out.println(solrClient);
    }
}
