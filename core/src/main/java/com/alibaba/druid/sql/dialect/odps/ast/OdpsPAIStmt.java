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
package com.alibaba.druid.sql.dialect.odps.ast;

import com.alibaba.druid.sql.dialect.odps.visitor.OdpsASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class OdpsPAIStmt extends OdpsStatementImpl {
    private String arguments;

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        accept0((OdpsASTVisitor) visitor);
    }

    public void accept0(OdpsASTVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
