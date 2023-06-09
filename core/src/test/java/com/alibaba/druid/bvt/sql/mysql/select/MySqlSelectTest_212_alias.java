/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.sql.mysql.select;

import com.alibaba.druid.sql.MysqlTest;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.SQLParserFeature;

import java.util.List;


public class MySqlSelectTest_212_alias extends MysqlTest {
    public void test_2() throws Exception {
        String sql = "SELECT count(1) from information_schema.tables;";

        System.out.println(sql);

        MySqlStatementParser parser = new MySqlStatementParser(sql, SQLParserFeature.SelectItemGenerateAlias);
        List<SQLStatement> statementList = parser.parseStatementList();

        assertEquals(1, statementList.size());

        SQLStatement stmt = statementList.get(0);

        assertEquals("SELECT count(1) AS `count(1)`\n" +
                "FROM information_schema.tables;", stmt.toString());
    }


    public void test_3() throws Exception {
        String sql = "select count(Distinct id) from t";

        MySqlStatementParser parser = new MySqlStatementParser(sql, SQLParserFeature.SelectItemGenerateAlias);
        List<SQLStatement> statementList = parser.parseStatementList();

        assertEquals(1, statementList.size());

        String text = output(statementList);
        assertEquals("SELECT count(DISTINCT id) AS `count(Distinct id)`\n" +
                "FROM t", text);
    }


}