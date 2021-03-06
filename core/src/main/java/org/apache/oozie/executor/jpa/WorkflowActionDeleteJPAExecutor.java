/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.oozie.executor.jpa;

import javax.persistence.EntityManager;

import org.apache.oozie.WorkflowActionBean;
import org.apache.oozie.util.ParamChecker;

public class WorkflowActionDeleteJPAExecutor implements JPAExecutor<Void> {

    private final String wfActionId;

    /**
     * @param wfActionId
     */
    public WorkflowActionDeleteJPAExecutor(String wfActionId) {
        ParamChecker.notEmpty(wfActionId, "ActionID");
        this.wfActionId = wfActionId;
    }

    @Override
    public Void execute(EntityManager em) throws JPAExecutorException {
        WorkflowActionBean action = em.find(WorkflowActionBean.class, this.wfActionId);
        if (action != null) {
            em.remove(action);
        }
        return null;
    }

    @Override
    public String getName() {
        return "WorkflowActionDeleteJPAExecutor";
    }
}
