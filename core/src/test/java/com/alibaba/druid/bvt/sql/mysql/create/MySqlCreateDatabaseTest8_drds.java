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
package com.alibaba.druid.bvt.sql.mysql.create;

import com.alibaba.druid.sql.MysqlTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

import java.util.List;

public class MySqlCreateDatabaseTest8_drds extends MysqlTest {
    @Test
    public void test_0() throws Exception {
        String sql = "CREATE DATABASE IF NOT EXISTS d1 DEFAULT CHARACTER SET = utf8mb4 \n" +
                "  PASSWORD = 'd1_pwd' STORED BY \n" +
                "    (IP = '127.0.0.1', PORT = 3306, USER = 'root', PASSWORD = '123456'),\n" +
                "    (IP = '127.0.0.1', PORT = 3307, USER = 'root', PASSWORD = '123456');";

        List<SQLStatement> stmtList = SQLUtils.toStatementList(sql, JdbcConstants.MYSQL);

        SQLStatement stmt = stmtList.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);

        String output = SQLUtils.toMySqlString(stmt);
        assertEquals("CREATE DATABASE IF NOT EXISTS d1 CHARACTER SET utf8mb4\n" +
                "STORED BY (IP = '127.0.0.1', PORT = 3306, USER = 'root', PASSWORD = '123456'),\n" +
                "\t(IP = '127.0.0.1', PORT = 3307, USER = 'root', PASSWORD = '123456');", output);
    }

}
