package com.xyg.hbase;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.Connection;


/**
 * Author: Mr.Deng
 * Date: 2018/8/15
 * Desc:
 */
public class HBaseDaoImpl implements HBaseDao {

    public static final Configuration conf;
    private String nameSpace;
    private String tableName;
    private Table table;
    private Connection connection;

    static {
        conf = HBaseConfiguration.create();
    }

}