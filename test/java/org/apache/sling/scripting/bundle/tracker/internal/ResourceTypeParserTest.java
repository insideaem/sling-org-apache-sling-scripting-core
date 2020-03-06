/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Licensed to the Apache Software Foundation (ASF) under one
 ~ or more contributor license agreements.  See the NOTICE file
 ~ distributed with this work for additional information
 ~ regarding copyright ownership.  The ASF licenses this file
 ~ to you under the Apache License, Version 2.0 (the
 ~ "License"); you may not use this file except in compliance
 ~ with the License.  You may obtain a copy of the License at
 ~
 ~   http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package org.apache.sling.scripting.bundle.tracker.internal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResourceTypeParserTest {

    @Test
    public void testSlashNoVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a/b/c");
        assertEquals("a/b/c", t1.getType());
        assertEquals("c", t1.getResourceLabel());
        assertNull(t1.getVersion());
    }

    @Test
    public void testSlashVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a/b/c/1.0.0");
        assertEquals("a/b/c", t1.getType());
        assertEquals("c", t1.getResourceLabel());
        assertEquals("1.0.0", t1.getVersion());
    }

    @Test
    public void testOneSegmentNoVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a");
        assertEquals("a", t1.getType());
        assertEquals("a", t1.getResourceLabel());
        assertNull(t1.getVersion());
    }

    @Test
    public void testOneSegmentVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a/1.2.3");
        assertEquals("a", t1.getType());
        assertEquals("a", t1.getResourceLabel());
        assertEquals("1.2.3", t1.getVersion());
    }

    @Test
    public void testDotNoVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a.b.c");
        assertEquals("a.b.c", t1.getType());
        assertEquals("c", t1.getResourceLabel());
        assertNull(t1.getVersion());
    }

    @Test
    public void testDotVersion() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType("a.b.c/42.0.0");
        assertEquals("a.b.c", t1.getType());
        assertEquals("c", t1.getResourceLabel());
        assertEquals("42.0.0", t1.getVersion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType(StringUtils.EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        ResourceTypeParser.ResourceType t1 = ResourceTypeParser.parseResourceType(null);
    }

}
