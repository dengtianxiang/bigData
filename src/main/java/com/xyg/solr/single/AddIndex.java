package com.xyg.solr.single;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;

/**
 * Author: Mr.Deng
 * Date: 2018/9/10
 * Desc: 添加索引
 */
public class AddIndex {

    @Test
    public void addIndexById() throws IOException, SolrServerException {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //创建索引文档对象
        SolrInputDocument doc = new SolrInputDocument();
        // 第一个参数：域的名称，域的名称必须是在schema.xml中定义的
        // 第二个参数：域的值,注意：id的域不能少
        doc.addField("id","1");
        doc.addField("name","红豆");
        doc.addField("price","1.2");
        //3.将文档写入索引库中
        solrClient.add(doc);
        solrClient.commit();
    }

    @Test
    public void addIndexByListId() throws Exception {
        String solrUrl = "http://node21:8080/solr/new_core";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //创建索引文档对象
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "2");
        doc1.addField( "name", "绿豆");
        doc1.addField( "price", 1.8 );
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField( "id", "3" );
        doc2.addField( "name", "黑豆" );
        doc2.addField( "price", 2.6 );
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add(doc1);
        docs.add(doc2);
        //3.将文档写入索引库中
        solrClient.add(docs);
        solrClient.commit();
    }

}
