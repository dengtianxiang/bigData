package com.xyg.solr.single;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import java.io.IOException;

/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 更新索引
 */
public class UpdateIndex {

    @Test
    public void updateIndex() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //创建索引文档对象
        SolrInputDocument doc = new SolrInputDocument();
        //把红豆价格修改为1.5
        doc.addField("id","1");
        doc.addField("name","红豆");
        doc.addField("price","1.5");
        //3.将文档写入索引库中
        solrClient.add(doc);
        solrClient.commit();
        //提交
        solrClient.commit();

    }

}
