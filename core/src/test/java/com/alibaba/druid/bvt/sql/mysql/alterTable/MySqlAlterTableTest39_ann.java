/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.druid.bvt.sql.mysql.alterTable;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

import java.util.List;

public class MySqlAlterTableTest39_ann extends TestCase {
    public void test_alter_modify_clustered_by() throws Exception {
        String sql = "ALTER TABLE face_feature ADD ANN INDEX facefea_index2  (facefea) DistanceMeasure = DotProduct ALGORITHM = IVF;";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        assertEquals(1, stmtList.size());
        SQLStatement stmt = stmtList.get(0);
        String output = SQLUtils.toMySqlString(stmt);
        assertEquals("ALTER TABLE face_feature\n" +
                "\tADD ANN INDEX facefea_index2 (facefea) ALGORITHM = IVF DistanceMeasure = DotProduct;", output);
    }
}
