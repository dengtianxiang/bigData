package com.xyg.solrCloud;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.junit.Test;

/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 测试连接客户端
 */
public class ConnectionCloudSolrClient {

    @Test
    public void connectionCloudSolrClient(){
        // 使用运行中的某一台solr节点
        //final String solrUrl = "http://192.168.100.21:8983/solr";
        //CloudSolrClient solrClient = new CloudSolrClient.Builder().withSolrUrl(solrUrl).build();

        // 使用zookeeper节点连接
        final String zkHost = "node21:2181,node22:2181,node23:2181/solr";
        CloudSolrClient solrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        System.out.println(solrClient);
    }
}
