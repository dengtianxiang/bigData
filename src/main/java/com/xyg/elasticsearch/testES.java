package com.xyg.elasticsearch;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testES {

	Client client = null;

	@Before
	public void conn() throws Exception{
        Settings settings = Settings.builder()
                .put("client.transport.sniff",true)
                .put("cluster.name", "myescluster")
                .build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("node21"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("node22"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("node23"), 9300));
	}


    @After
	public void close(){
		client.close();
	}

	@Test
	public void test01(){
		//创建索引库   执行过程类似懒加载
		IndicesExistsResponse existsResponse = client.admin().indices().prepareExists("javatest").execute().actionGet();
		if(existsResponse.isExists()){
			client.admin().indices().prepareDelete("javatest").execute();
		}
		Map<String,Object> sets = new HashMap<String,Object>();
        //设置副本数
		sets.put("number_of_replicas", "2");
		client.admin().indices().prepareCreate("javatest").setSettings(sets).execute();
	}

	@Test
	public void test02(){
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("content", "hbase is hadoop");
		data.put("path", "/books/hive");
		data.put("len", 2222);
		IndexResponse resp = client.prepareIndex("javatest", "god").setSource(data).execute().actionGet();
		System.out.println(resp.getId());
	}



}