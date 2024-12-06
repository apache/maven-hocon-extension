/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.hocon;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.maven.api.model.Dependency;
import org.apache.maven.api.model.Model;
import org.apache.maven.api.model.Plugin;
import org.apache.maven.api.xml.XmlNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParsingTest {

    @Test
    void testParse() throws Exception {
        Config config = ConfigFactory.parseFile(new File("src/test/resources/pom.hocon"));

        Model model = new HoconReader().parseModel(config.root());

        assertEquals("40", model.getParent().getVersion());
        assertEquals(1, model.getDependencies().size());
        assertEquals(2, model.getProperties().size());
    }

    @Test
    void testParse2() throws Exception {
        Config config = ConfigFactory.parseFile(new File("src/test/resources/pom2.hocon"));

        Model model = new HoconReader().parseModel(config.root());

        assertEquals("40", model.getParent().getVersion());
        assertEquals(2, model.getDependencies().size());
        Dependency dependency = model.getDependencies().get(0);
        assertEquals("org.apache.maven", dependency.getGroupId());
        assertEquals("maven-api-core", dependency.getArtifactId());
        assertEquals("${maven.version}", dependency.getVersion());
        assertEquals(1, model.getBuild().getPlugins().size());
        Plugin plugin = model.getBuild().getPlugins().get(0);
        assertEquals("org.apache.maven", plugin.getGroupId());
        assertEquals("maven-compiler-plugin", plugin.getArtifactId());
        assertEquals("${maven.compiler.version}", plugin.getVersion());
        XmlNode node = plugin.getConfiguration();
        assertNotNull(node);
        assertEquals("17", node.getChild("release").getValue());
    }
}
