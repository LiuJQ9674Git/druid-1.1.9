package com.alibaba.druid.bvt.sql.mysql.param;

import com.alibaba.fastjson2.JSON;
import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.sql.visitor.VisitorFeature;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 16/9/23.
 */
public class MySqlParameterizedOutputVisitorTest_62 extends TestCase {
    public void test_for_parameterize() throws Exception {
        String sql = "select abc.* from abc join t_1 on abc.name = t_1.id2 where t_1.fname like 'hz.%'";

        List<Object> params = new ArrayList<Object>();
        String psql = ParameterizedOutputVisitorUtils.parameterize(sql, JdbcConstants.MYSQL, params, VisitorFeature.OutputParameterizedUnMergeShardingTable);
        assertEquals("SELECT abc.*\n" +
                "FROM abc\n" +
                "\tJOIN t_1 ON abc.name = t_1.id2\n" +
                "WHERE t_1.fname LIKE ?", psql);
        assertEquals(1, params.size());
        assertEquals("\"hz.%\"", JSON.toJSONString(params.get(0)));

        String rsql = ParameterizedOutputVisitorUtils.restore(sql, JdbcConstants.MYSQL, params);
        assertEquals("SELECT abc.*\n" +
                "FROM abc\n" +
                "\tJOIN t_1 ON abc.name = t_1.id2\n" +
                "WHERE t_1.fname LIKE 'hz.%'", rsql);
    }

}
