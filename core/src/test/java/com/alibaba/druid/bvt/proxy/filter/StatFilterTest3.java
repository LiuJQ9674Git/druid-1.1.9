package com.alibaba.druid.bvt.proxy.filter;

import com.alibaba.druid.DbType;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.druid.filter.stat.StatFilter;

public class StatFilterTest3 extends TestCase {
    @SuppressWarnings("deprecation")
    public void test_dbType() throws Exception {
        StatFilter filter = new StatFilter();

        Assert.assertFalse(filter.isMergeSql());

        filter.setDbType("mysql");
        filter.setMergeSql(true);

        Assert.assertTrue(filter.isMergeSql());
        Assert.assertEquals(DbType.mysql, filter.getDbType());

        Assert.assertEquals("SELECT ?\nLIMIT ?", filter.mergeSql("select 'x' limit 1"));
    }

    public void test_dbType_error() throws Exception {
        StatFilter filter = new StatFilter();
        filter.setDbType("mysql");
        filter.setMergeSql(true);

        Assert.assertEquals(DbType.mysql, filter.getDbType());

        Assert.assertEquals("sdafawer asf ", filter.mergeSql("sdafawer asf "));
    }

    public void test_merge() throws Exception {
        StatFilter filter = new StatFilter();
        filter.setDbType("mysql");
        filter.setMergeSql(false);

        Assert.assertEquals(DbType.mysql, filter.getDbType());

        Assert.assertEquals("select 'x' limit 1", filter.mergeSql("select 'x' limit 1"));
    }


    public void test_merge_pg() throws Exception {
        StatFilter filter = new StatFilter();
        filter.setDbType(JdbcConstants.POSTGRESQL);
        filter.setMergeSql(true);

        Assert.assertEquals(JdbcConstants.POSTGRESQL, filter.getDbType());

        Assert.assertEquals("DROP TABLE IF EXISTS test_site_data_select_111;\n" +
                "CREATE TABLE test_site_data_select_111\n" +
                "AS\n" +
                "SELECT *\n" +
                "FROM postman_trace_info_one\n" +
                "WHERE lng > ?\n" +
                "\tAND lat > ?\n" +
                "\tAND site_id = ?;", filter.mergeSql("drop table if exists test_site_data_select_111; create table test_site_data_select_111 AS select * from postman_trace_info_one  where lng>0 and lat>0  and site_id='17814' ;", JdbcConstants.POSTGRESQL));
    }

    public void test_merge_oracle() throws Exception {
        StatFilter filter = new StatFilter();
        filter.setDbType(DbType.oceanbase_oracle);
        filter.setMergeSql(true);

        filter.mergeSql("insert into t(f1, f2) values (1, 2)", DbType.oceanbase_oracle);
    }

    public void test_merge_nodbtype() throws Exception {
        StatFilter filter = new StatFilter();

        Assert.assertFalse(filter.isMergeSql());

        filter.setMergeSql(true);

        Assert.assertTrue(filter.isMergeSql());
        Assert.assertNull(filter.getDbType());

        Assert.assertEquals("SELECT *\n" +
                        "FROM temp.test\n" +
                        "ORDER BY id DESC\n" +
                        "LIMIT ?"
                , filter.mergeSql("select * from temp.test order by id desc limit 1"));
    }
}
