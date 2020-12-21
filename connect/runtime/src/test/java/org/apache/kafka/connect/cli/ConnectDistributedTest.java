/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.connect.cli;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConnectDistributedTest {
    @Test
    public void testGetPropsFromArgs() throws Exception {
        String[] args = new String[]{"../../config/connect-distributed.properties",
                "--override", "bootstrap.servers=localhost:9192",
                "--override", "plugin.path=/opt/plugins"};
        Map<String, String> workerProps = ConnectDistributed.getPropsFromArgs(args);

        // check those properties from the file
        assertEquals("connect-cluster", workerProps.get("group.id"));
        // check those overriden properties
        assertEquals("localhost:9192", workerProps.get("bootstrap.servers"));
        assertEquals("/opt/plugins", workerProps.get("plugin.path"));
    }
}
