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
package com.alibaba.druid.sql.dialect.oscar.ast.stmt;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.oscar.visitor.OscarASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class OscarSelectStatement extends SQLSelectStatement implements OscarStatement {
    public OscarSelectStatement() {
        super(DbType.oscar);
    }

    public OscarSelectStatement(SQLSelect select) {
        super(select, DbType.oscar);
    }

    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof OscarASTVisitor) {
            accept0((OscarASTVisitor) visitor);
        } else {
            super.accept0(visitor);
        }
    }

    public void accept0(OscarASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.select);
        }
        visitor.endVisit(this);
    }
}
