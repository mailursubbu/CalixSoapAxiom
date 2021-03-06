/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axiom.ts.fom.feed;

import static com.google.common.truth.Truth.assertThat;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.axiom.ts.fom.AbderaTestCase;

public abstract class AddChildWithExistingEntryTestCase extends AbderaTestCase {
    public AddChildWithExistingEntryTestCase(Abdera abdera) {
        super(abdera);
    }

    @Override
    protected void runTest() throws Throwable {
        Feed feed = abdera.getFactory().newFeed();
        Entry entry = feed.addEntry();
        Element addedChild = addChild(feed);
        Element child = feed.getFirstChild();
        // Entries must come after any other type of child elements
        assertThat(child).isSameAs(addedChild);
        assertThat(child.getNextSibling()).isSameAs(entry);
    }
    
    protected abstract Element addChild(Feed feed);
}
